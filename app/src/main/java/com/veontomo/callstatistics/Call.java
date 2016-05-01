package com.veontomo.callstatistics;

import android.os.Build;
import android.telephony.PhoneNumberUtils;
import android.util.Log;

/**
 * Collects simple information about phone call.
 * <p/>
 * The current implementation uses only the phone number and the name associated with it,
 * and ignores all other information such as call duration, call direction (incoming/outcoming),
 * call time etc.
 */
public class Call {
    private static final String TAG = "CallStat";
    public final String callNumber;
    public final String name;

    public Call(String callNumber, String name) {
        this.callNumber = callNumber;
        this.name = name;

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            // Log.i(TAG, "Call: formatted number " + PhoneNumberUtils.formatNumber(callNumber, "IT"));
        } else {
             // Log.i(TAG, "Call: formatted number " + PhoneNumberUtils.formatNumber(callNumber));

        }



    }


    public String toString() {
        return "phone number: " + callNumber;
    }
}
