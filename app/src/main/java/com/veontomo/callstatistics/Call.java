package com.veontomo.callstatistics;

/**
 * Collects phone call numbers.
 */
public class Call {
    private final String callNumber;
    private final String callType;
    private final int direction;
    private final int duration;
    private final long time;

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
