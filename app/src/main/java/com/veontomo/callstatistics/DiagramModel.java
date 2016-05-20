package com.veontomo.callstatistics;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import com.google.i18n.phonenumbers.NumberParseException;
import com.google.i18n.phonenumbers.PhoneNumberUtil;
import com.google.i18n.phonenumbers.Phonenumber;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.subjects.PublishSubject;


/**
 * Diagram model of MVP architecture
 */
class DiagramModel {

    private final Presenter mPresenter;

    private final List<Call> mCalls;

    private final String TAG = Config.appName;

    private final PublishSubject<Call> mStream;

    private CallHistogram mHistogram;

    public DiagramModel(final Presenter presenter) {
        mPresenter = presenter;
        mCalls = new ArrayList<>();

        mStream = PublishSubject.create();

        final Subscriber<Call> mCallsReceiver = new Subscriber<Call>() {

            /**
             * Notifies the Observer that the {@link Observable} has finished sending push-based notifications.
             * <p/>
             * The {@link Observable} will not call this method if it calls {@link #onError}.
             */
            @Override
            public void onCompleted() {
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
        if (mHistogram != null) {
            onDataPrepared();
        } else {
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
            mPresenter.onError("Missing permissions to read the call log.");
            return;
        }
        final Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        if (cursor == null) {
            mPresenter.onError("Can not read from the call log.");
            return;
        }
        int normalizedNumberColumn = 0;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
            normalizedNumberColumn = cursor.getColumnIndex(CallLog.Calls.CACHED_NORMALIZED_NUMBER);
        }
        int numberColumn = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int nameColumn = cursor.getColumnIndex(CallLog.Calls.CACHED_NAME);
        String number;
        PhoneNumberUtil phoneUtil = PhoneNumberUtil.getInstance();
        Phonenumber.PhoneNumber numberFormatted;
        String locale = context.getResources().getConfiguration().locale.getCountry();
        boolean hasCountryCode = locale != null && !(locale.isEmpty());
        while (cursor.moveToNext()) {
            number = cursor.getString(normalizedNumberColumn);
            if (number == null) {
                number = cursor.getString(numberColumn);
            }
            try {
                if (hasCountryCode) {
                    numberFormatted = phoneUtil.parse(number, locale);
                    if (numberFormatted != null) {
                        number = String.valueOf(numberFormatted.getNationalNumber());
                    }
                }
            } catch (NumberParseException e) {
                Log.i(TAG, "readCallLog: error when parsing a phone number " + number + " with locale " + locale + " at position " + cursor.getPosition() + ": " + e.getMessage());

            }

            Call c = new Call(number, cursor.getString(nameColumn));
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
