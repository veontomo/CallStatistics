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
        int newPos;
        float freq;
        if (data == null){
            mSize = 0;
            return;
        }
        for (Call c : data) {
            if (x.contains(c.callNumber)) {
                pos = x.indexOf(c.callNumber);
                freq = y.get(pos) + 1;
                y.set(pos, freq);
                newPos = findNewPosition(pos, y);
                if (newPos != pos) {
                    String xVal = x.remove(pos);
                    float yVal = y.remove(pos);
                    x.add(newPos, xVal);
                    y.add(newPos, yVal);
                }
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
     * Finds a  position at which the element with index pos, must be at.
     * All elements apart from the one at position pos, are sorted in a decreasing order.
     * The aim is to find a proper place for that element in such a way that ALL elements
     * are sorted in decreasing order.
     *
     * @param pos  position of the element due to which the list is not sorted.
     * @param data list of float in almost decreasing order apart from possible one element
     */
    public int findNewPosition(int pos, final List<Float> data) {
        float value = data.get(pos);
        int newPos = pos - 1;
        while (newPos >= 0 && value > data.get(newPos)) {
            newPos--;
        }
        return newPos + 1;


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
