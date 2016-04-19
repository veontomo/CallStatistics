package com.veontomo.callstatistics;

/**
 * StatisticsData data interface.
 * Data contains pairs with the keys being of type T and the values being of type K.
 *
 * It is supposed that objects of type type K
 *
 */
public interface StatisticsData<T, K> {
    /**
     * Gets the maximum among the values
     * @return
     */
    K getMax();

    /**
     * Gets the minimum among the values
     * @return
     */
    K getMin();

    /**
     * Converts value-type into a float (in order to be able to display it on a chart)
     * @param k
     * @return
     */
    float toFloat(K k);

    /**
     * Returns the number of key-value pairs
     * @return
     */
    int getSize();


    /**
     * Returns a key of the i-th element
     * @param i
     * @return
     */
    T getKey(int i);

    /**
     * Returns a value of the i-th element
     * @param i
     * @return
     */
    K getValue(int i);

}
