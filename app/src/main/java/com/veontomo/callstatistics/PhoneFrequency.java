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
        for (Call c : data) {
            if (x.contains(c.callNumber)) {
                pos = x.indexOf(c.callNumber);
                freq = y.get(pos) + 1;
                y.set(pos, freq);
                newPos = findPos(pos, y);
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
    public int findPos(int pos, final List<Float> data) {
        float value = data.get(pos);
        int newPos = pos - 1;
        while (newPos >= 0 && value > data.get(newPos)) {
            newPos--;
        }
        return newPos + 1;


    }

    /**
     * Adjust the lists x, y in such a way that x is sorted in decreasing order while maintaining
     * initial relationship with list y.
     * Constraint: if the element at position pos is thrown away, then list y is sorted in decreasing order.
     *
     * @param pos
     */
    public void adjust(int posNew, int pos,  List<String> x, List<Float> y) {
        String xVal = x.remove(pos);
        float yVal = y.remove(pos);
        x.add(posNew, xVal);
        y.add(posNew, yVal);
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
