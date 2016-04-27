package com.veontomo.callstatistics;

/**
 * Collects simple information about phone call.
 *
 * The current implementation uses only the phone number and ignores all other information such
 * as call duration, call direction (incoming/outcoming), call time etc.
 */
public class Call {
    public final String callNumber;

    public Call(String callNumber) {
        this.callNumber = callNumber;
    }

    public String toString(){
        return "phone number: " + callNumber;
    }
}
