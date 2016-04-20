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
    private int mSize;

    private String[] xValues;

    private Integer[] yValues;

    private Integer yMax = null;

    private Integer yMin = null;

    public PhoneFrequency(final List<Call> data) {
        List<String> x = new ArrayList<>();
        List<Integer> y = new ArrayList<>();
        int pos;
        int newPos;
        int freq;
        if (data == null) {
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
                    int yVal = y.remove(pos);
                    x.add(newPos, xVal);
                    y.add(newPos, yVal);
                }
            } else {
                freq = 1;
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
        yValues = new Integer[mSize];
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
    public int findNewPosition(int pos, final List<Integer> data) {
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
    public int getY(int i) {
        return yValues[i];
    }

    /**
     * Returns the maximal of y-values
     *
     * @return
     */
    @Override
    public int getMax() {
        return yMax;
    }

    /**
     * Returns the minimal of y-values
     *
     * @return
     */
    @Override
    public int getMin() {
        return yMin;
    }

    /**
     * Removes the elements whose y-value is less than given cutoff.
     *
     * @param cutoff
     */
    @Override
    public void truncate(int cutoff) {

    }


    /**
     * Returns the index of the element starting from which all elements have y-value less than the
     * cutoff.
     *
     * @param needle
     * @param data   sorted list in decreasing order.
     * @return
     */
    public int findSmallerThan(int needle, Integer[] data) {
        int leftBound = 0;
        int rightBound = data.length - 1;
        int pointer = (leftBound + rightBound) / 2;
        float value;
        while (rightBound - leftBound > 1) {
            value = data[pointer];
            if (value < needle) {
                rightBound = pointer;
            } else {
                leftBound = pointer;
            }
            pointer = (leftBound + rightBound) / 2;
        }
        if (rightBound == leftBound) {
            if (data[rightBound] > needle) {
                return rightBound + 1;
            }
            return rightBound;
        }
        if (data[leftBound] == data[rightBound]) {

            return leftBound;
        }
        if (data[leftBound] == needle) {
            return leftBound + 1;
        }
        if (data[rightBound] > needle){
            return rightBound + 1;
        }
        if (data[rightBound] == needle){
            return rightBound;
        }
        return leftBound;
    }

}
