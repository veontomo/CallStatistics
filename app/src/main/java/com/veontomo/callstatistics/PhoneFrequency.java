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

    private Float yMax = null;

    private Float yMin = null;

    public PhoneFrequency(final List<Call> data) {
        List<String> x = new ArrayList<>();
        List<Float> y = new ArrayList<>();
        int pos;
        float freq;
        for (Call c : data) {
            if (x.contains(c.callNumber)) {
                pos = x.indexOf(c.callNumber);
                freq = y.get(pos) + 1;
                y.set(pos, freq);
                adjust(pos, x, y);
            } else {
                freq = 1f;
                x.add(c.callNumber);
                y.add(freq);
            }
            if (yMax == null || yMax < freq) {
                yMax = freq;
            }
            if (yMin == null || yMin > freq) {
                yMin = freq;
            }
        }

        mSize = x.size();
        xValues = new String[mSize];
        xValues = x.toArray(xValues);
        yValues = new Float[mSize];
        yValues = y.toArray(yValues);

    }

    /**
     * Adjust the lists x, y in such a way that x is sorted in decreasing order while maintaining
     * initial relationship with list y.
     * Constraint: if the element at position pos is thrown away, then list y is sorted in decreasing order.
     *
     * @param pos
     */
    private void adjust(int pos, List<String> x, List<Float> y) {
        String xVal = x.remove(pos);
        float yVal = y.remove(pos);
        int newPos = pos - 1;
        while (newPos >= 0 && yVal > y.get(newPos)){
            newPos--;
        }
        if (newPos == -1){
            newPos = 0;
        }
        x.add(newPos, xVal);
        y.add(newPos, yVal);
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

    /**
     * Returns the maximal of y-values
     *
     * @return
     */
    @Override
    public float getMax() {
        return yMax;
    }

    /**
     * Returns the minimal of y-values
     *
     * @return
     */
    @Override
    public float getMin() {
        return yMin;
    }
}
