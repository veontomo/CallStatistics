package com.veontomo.callstatistics;

import java.util.ArrayList;

/**
 * Diagram data that shows how often a phone number occurs in phone call registry.
 *
 */
public class PhoneFrequency implements DiagramData {

    /**
     * number of elements in the diagram data
     */
    private final int mSize;

    public PhoneFrequency(ArrayList<Call> data) {
        /// TODO: implements this method
        this.mSize = 0;
    }

    /**
     * The number of the elements
     *
     * @return
     */
    @Override
    public int getSize() {
        return this.mSize;
    }

    /**
     * x-value of the element at a given position
     *
     * @param i
     * @return
     */
    @Override
    public String getX(int i) {
        return null;
    }

    /**
     * y-value of the element at a given position
     *
     * @param i
     * @return
     */
    @Override
    public float getY(int i) {
        return 0;
    }
}
