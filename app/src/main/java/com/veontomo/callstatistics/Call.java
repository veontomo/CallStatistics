package com.veontomo.callstatistics;

/**
 * Collects simple information about phone call.
 * <p/>
 * The current implementation uses only the phone number and the name associated with it,
 * and ignores all other information such as call duration, call direction (incoming/outcoming),
 * call time etc.
 */
public class Call {
    public final String callNumber;
    public final String name;

    public Call(String callNumber, String name) {
        this.callNumber = callNumber;
        this.name = name;
    }


    public String toString() {
        return "phone number: " + callNumber;
    }
}
