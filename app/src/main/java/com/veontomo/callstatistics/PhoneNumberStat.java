package com.veontomo.callstatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Phone numbers' frequencies
 */
public class PhoneNumberStat {

    private List<Call> items;

    public PhoneNumberStat() {
        this.items = new ArrayList<>();
    }

    /**

     * Gets the maximum among the values
     *
     * @return
     */
    public Float getMax() {
        return null;
    }

    /**
     * Gets the minimum among the values
     *
     * @return
     */
    public Float getMin() {
        return null;
    }

    /**
     * Converts value-type into a float (in order to be able to display it on a chart)
     *
     * @param aFloat
     * @return
     */
    public float toFloat(Float aFloat) {
        return 0;
    }

    /**
     * Returns the number of key-value pairs
     *
     * @return
     */
    public int getSize() {
        return 0;
    }

    /**
     * Returns
     *
     * @param i
     * @return
     */
    public String getKey(int i) {
        return null;
    }

    /**
     * Returns a value of the i-th element
     *
     * @param i
     * @return
     */
    public Float getValue(int i) {
        return null;
    }


    public void add(Call c) {
        items.add(c);


    }
}
