package com.veontomo.callstatistics;

import java.util.ArrayList;
import java.util.List;

/**
 * Diagram data that shows how often a phone number occurs in phone call registry.
 */
public class PhoneFrequency implements DiagramData {

    /**
     * number of elements in the diagram data
     */
    private final int mSize;

    private String[] xValues;

    private Float[] yValues;

    public PhoneFrequency(final ArrayList<Call> data) {
        List<String> x = new ArrayList<>();
        List<Float> y = new ArrayList<>();
        int pos;
        float yTmp;
        for (Call c : data) {
            if (x.contains(c.callNumber)) {
                pos = x.indexOf(c.callNumber);
                yTmp = y.get(pos);
                y.set(pos, yTmp + 1);
            } else {
                x.add(c.callNumber);
                y.add(1f);
            }
        }
        mSize = x.size();
        xValues = new String[mSize];
        xValues = x.toArray(xValues);
        yValues = new Float[mSize];
        yValues = y.toArray(yValues);

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
        return xValues[i];
    }

    /**
     * y-value of the element at a given position
     *
     * @param i
     * @return
     */
    @Override
    public float getY(int i) {
        return yValues[i];
    }
}
