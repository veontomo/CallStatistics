package com.veontomo.callstatistics;

/**
 * Defines data to display on the diagram.
 */
public interface DiagramData {
    /**
     * The number of the elements
     * @return
     */
    int getSize();

    /**
     * x-value of the element at a given position
     * @param i
     * @return
     */
    String getX(int i);

    /**
     * y-value of the element at a given position
     * @param i
     * @return
     */
    float getY(int i);

    /**
     * Returns the maximal of y-values
     * @return
     */
    float getMax();


    /**
     * Returns the minimal of y-values
     * @return
     */
    float getMin();

}
