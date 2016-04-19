package com.veontomo.callstatistics;

/**
 * Collects phone call numbers.
 */
public class Call {
    public final String callNumber;
    public final String callType;
    public final int direction;
    public final int duration;
    public final long time;

    public Call(String callNumber, String callType, int direction, int duration, long time) {
        this.callNumber = callNumber;
        this.callType = callType;
        this.direction = direction;
        this.duration = duration;
        this.time = time;
    }

    public String toString(){
        return callNumber + " " + callType + " " + direction + " " + duration + " " + time;
    }
}
