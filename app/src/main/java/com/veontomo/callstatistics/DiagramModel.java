package com.veontomo.callstatistics;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;


/**
 * Diagram model of MVP architecture
 */
public class DiagramModel {

    private final Presenter mPresenter;

    private final List<Call> mCalls;

    private final Subscriber<Call> mCallsReceiver;

    private final String TAG = Config.appName;

    private final PublishSubject<Call> mStream;

    private CallHistogram mHistogram;

    public DiagramModel(final Presenter presenter) {
        mPresenter = presenter;
        mCalls = new ArrayList<>();

        mStream = PublishSubject.create();

        mCallsReceiver = new Subscriber<Call>() {

            /**
             * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
             * <p/>
             * The {@link Observable} will not call this method if it calls {@link #onError}.
             */
            @Override
            public void onCompleted() {
                Log.i(TAG, "onCompleted: mCallsReceiver is done");
                mHistogram = new CallHistogram(mCalls);
                onDataPrepared();

            }

            /**
             * Notifies the Observer that the {@link Observable} has experienced an error condition.
             * <p/>
             * If the {@link Observable} calls this method, it will not thereafter call {@link #onNext} or
             * {@link #onCompleted}.
             *
             * @param e the exception encountered by the Observable
             */
            @Override
            public void onError(Throwable e) {
                Log.i(TAG, "onError: mCallsReceiver has thrown an error: " + e.getMessage());
                mPresenter.onError("Failed to add a Call instance to the stream");
            }

            /**
             * Provides the Observer with a new item to observe.
             * <p/>
             * The {@link Observable} may call this method 0 or more times.
             * <p/>
             * The {@code Observable} will not call this method again after it calls either {@link #onCompleted} or
             * {@link #onError}.
             *
             * @param call the item emitted by the Observable
             */
            @Override
            public void onNext(Call call) {
//                Log.i(TAG, "onNext: call data " + call.toString());
                mCalls.add(call);
            }
        };
        mStream
// TODO: find out why assigning the thread leads to the fact that mStream methods onNext and onCompleted get never called
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCallsReceiver);
    }

    /**
     * Prepares the data to display
     */
    public void prepareData() {
        Log.i(TAG, "the model has received a request to prepare the data");

        if (mHistogram != null) {
            Log.i(TAG, "prepareData: histogram contains " + mHistogram.getSize());
            onDataPrepared();
        } else {
            Log.i(TAG, "prepareData: histogram is null");
            final Context context = mPresenter.getAppContext();
            if (context != null) {
                readCallLog(context, mStream);
                mStream.onCompleted();
            }
        }


    }

    /**
     * Passes the prepared data to the presenter.
     */
    private void onDataPrepared() {
        if (mPresenter != null) {
            mPresenter.loadData(mHistogram);
            mPresenter.loadListData(mHistogram);

        }
    }

    /**
     * Reading the call log.
     * <p/>
     * Each call log data is transformed in a Call instance and passed to the RxJava stream receiver.
     *
     * @param context
     * @param stream
     */
    private void readCallLog(final Context context, final PublishSubject<Call> stream) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Log.i(TAG, "no permissions");
            return;
        }
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor == null) {
            Log.i(TAG, "The cursor is null, so no data can be read.");
            return;
        }
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        while (cursor.moveToNext()) {
            Call c = new Call(cursor.getString(number));
            stream.onNext(c);
        }
        cursor.close();
    }

    public void setCutoff(int cutoff) {
        mHistogram = new CallHistogram(mCalls);
        mHistogram.truncate(cutoff);
        onDataPrepared();
    }
}
