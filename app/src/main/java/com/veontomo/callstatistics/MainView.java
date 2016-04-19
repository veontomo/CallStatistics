package com.veontomo.callstatistics;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import java.util.Date;

public class MainView extends AppCompatActivity {

    private DiagramView mView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_view);
        calllog();
        mView = (DiagramView) findViewById(R.id.diagramView);
    }



    public void update(View v){
        if (mView != null){
            mView.loadData();
        }
    }


    private void calllog() {
        Log.i(Config.appName, "last call: " + CallLog.Calls.getLastOutgoingCall(this));
        StringBuffer stringBuffer = new StringBuffer();
        //if (checkPermission())
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            Log.i(Config.appName, "no permissions");
            return;
        }
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI,
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
    }


}
