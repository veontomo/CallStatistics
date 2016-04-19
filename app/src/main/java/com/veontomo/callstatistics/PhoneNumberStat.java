package com.veontomo.callstatistics;

/**
 * Phone numbers' frequencies
 */
public class PhoneNumberStat implements StatisticsData<String, Float> {
    /**
     * Gets the maximum among the values
     *
     * @return
     */
    @Override
    public Float getMax() {
        return null;
    }

    /**
     * Gets the minimum among the values
     *
     * @return
     */
    @Override
    public Float getMin() {
        return null;
    }

    /**
     * Converts value-type into a float (in order to be able to display it on a chart)
     *
     * @param aFloat
     * @return
     */
    @Override
    public float toFloat(Float aFloat) {
        return 0;
    }

    /**
     * Returns the number of key-value pairs
     *
     * @return
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * Returns
     *
     * @param i
     * @return
     */
    @Override
    public String getKey(int i) {
        return null;
    }

    /**
     * Returns a value of the i-th element
     *
     * @param i
     * @return
     */
    @Override
    public Float getValue(int i) {
        return null;
    }


}
