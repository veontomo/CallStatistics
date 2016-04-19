package com.veontomo.callstatistics;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.Date;

/**
 * Model of MVP architecture
 */
public class Model {

    private final Presenter mPresenter;

    public Model(final Presenter presenter) {
        mPresenter = presenter;
    }

    /**
     * Prepares the data to dispaly
     */
    public void prepareData() {
        Log.i(Config.appName, "the model has received a request to prepare the data");
        PhoneNumberStat stat = phoneCallStat();
        onDataPrepared(stat);
    }

    /**
     * Passes the prepared data to the presenter.
     *
     * @param stat
     */
    private void onDataPrepared(PhoneNumberStat stat) {
        if (mPresenter != null) {
            mPresenter.loadData(stat);
        }
    }

    private PhoneNumberStat phoneCallStat() {
        final Context context = mPresenter.getAppContext();
        Log.i(Config.appName, "last call: " + CallLog.Calls.getLastOutgoingCall(context));
        StringBuffer stringBuffer = new StringBuffer();
        //if (checkPermission())
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Log.i(Config.appName, "no permissions");
            return null;
        }
        PhoneNumberStat result = new PhoneNumberStat();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI,
                null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int counter = 0;
        String phNumber;
        String callType;
        String callDate;
        Date callDayTime;
        String callDuration;
        String dir;
        while (cursor.moveToNext()) {
            counter++;
            phNumber = cursor.getString(number);
            callType = cursor.getString(type);
            callDate = cursor.getString(date);
            callDayTime = new Date(Long.valueOf(callDate));
            callDuration = cursor.getString(duration);
            dir = null;
            int dircode = Integer.parseInt(callType);
            switch (dircode) {
                case CallLog.Calls.OUTGOING_TYPE:
                    dir = "OUTGOING";
                    break;
                case CallLog.Calls.INCOMING_TYPE:
                    dir = "INCOMING";
                    break;

                case CallLog.Calls.MISSED_TYPE:
                    dir = "MISSED";
                    break;
            }
            Log.i(Config.appName, String.valueOf(counter));
            Log.i(Config.appName, "Phone Number:--- " + phNumber + " \nCall Type:--- " + dir + " \nCall Date:--- " + callDayTime
                    + " \nCall duration in sec :--- " + callDuration);
        }
        cursor.close();
        return result;
    }

}
