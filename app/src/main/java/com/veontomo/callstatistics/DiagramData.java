package com.veontomo.callstatistics;

/**
 * Defines data to display on the diagram.
 */
interface DiagramData {
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
    int getY(int i);

    /**
     * Returns the maximal of y-values
     * @return
     */
    int getMax();







    String getLegend(int pos);

}
