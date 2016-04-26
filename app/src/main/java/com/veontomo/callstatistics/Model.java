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
 * Model of MVP architecture
 */
public class Model {

    private final Presenter mPresenter;

    private final List<Call> mCalls;

    private final Subscriber<Call> mCallsReceiver;

    private final String TAG = Config.appName;

    private final PublishSubject<Call> mSubject;

    private CallHistogram mHistogram;

    public Model(final Presenter presenter) {
        mPresenter = presenter;
        mCalls = new ArrayList<>();

        mSubject = PublishSubject.create();

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
                onDataPrepared(mHistogram);

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
                Log.i(TAG, "onNext: call data " + call.toString());
                mCalls.add(call);
            }
        };
        mSubject
// TODO: find out why assigning the thread leads to the fact that mSubject methods onNext and onCompleted get never called
//                    .subscribeOn(Schedulers.io())
//                    .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mCallsReceiver);
    }

    /**
     * Prepares the data to display
     */
    public void prepareData() {
        Log.i(Config.appName, "the model has received a request to prepare the data");

        if (mHistogram != null) {
            Log.i(TAG, "prepareData: histogram contains " + mHistogram.getSize());
            onDataPrepared(mHistogram);
        } else {
            Log.i(TAG, "prepareData: histogram is null");
            final Context context = mPresenter.getAppContext();
            if (context != null) {
                readCallLog(context, mSubject);
                mSubject.onCompleted();
            }
        }


    }

    /**
     * Passes the prepared data to the presenter.
     *
     * @param data
     */
    private void onDataPrepared(DiagramData data) {
        if (mPresenter != null) {
            mPresenter.loadData(data);

        }
    }

    /**
     * Reading the call log.
     * <p/>
     * Each call log data is transformed in a Call instance and passed to the RxJava stream receiver.
     * @param context
     * @param stream
     */
    private void readCallLog(final Context context, final PublishSubject<Call> stream) {
        Log.i(TAG, "readCallLog: start");

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
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        String phNumber;
        String callType;
        String callDate;
        String callDuration;
        while (cursor.moveToNext()) {
            phNumber = cursor.getString(number);
            callType = cursor.getString(type);
            callDate = cursor.getString(date);
            callDuration = cursor.getString(duration);
            Call c = new Call(phNumber, callType, Integer.parseInt(callType), Integer.parseInt(callDuration), Long.valueOf(callDate));
            stream.onNext(c);

        }
        cursor.close();
    }

}
