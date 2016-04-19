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
}
