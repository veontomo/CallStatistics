package com.veontomo.callstatistics;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.util.Log;

import java.util.ArrayList;

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
        ArrayList<Call> stat = phoneCallStat();
        onDataPrepared(stat);
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

    private ArrayList<Call> phoneCallStat() {
        final Context context = mPresenter.getAppContext();
        Log.i(Config.appName, "last call: " + CallLog.Calls.getLastOutgoingCall(context));
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            Log.i(Config.appName, "no permissions");
            return null;
        }
        final ArrayList<Call> result = new ArrayList<>();
        Cursor cursor = context.getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");
        int number = cursor.getColumnIndex(CallLog.Calls.NUMBER);
        int type = cursor.getColumnIndex(CallLog.Calls.TYPE);
        int date = cursor.getColumnIndex(CallLog.Calls.DATE);
        int duration = cursor.getColumnIndex(CallLog.Calls.DURATION);
        int counter = 0;
        String phNumber;
        String callType;
        String callDate;
        String callDuration;
        while (cursor.moveToNext()) {
            counter++;
            phNumber = cursor.getString(number);
            callType = cursor.getString(type);
            callDate = cursor.getString(date);
            callDuration = cursor.getString(duration);
            Call c = new Call(phNumber, callType, Integer.parseInt(callType), Integer.parseInt(callDuration), Long.valueOf(callDate));
            Log.i(Config.appName, String.valueOf(counter) + ": " + c.toString());
            result.add(c);

        }
        cursor.close();
        return result;
    }

}
