package com.veontomo.callstatistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for methods of {@link CallHistogram} class.
 */
public class CallHistogramTest {

    @Test
    public void testEmptyData() {
        CallHistogram diagram = new CallHistogram(new ArrayList<Call>());
        assertEquals(0, diagram.getSize());
    }

    @Test
    public void testSizeAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("c", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals(3, diagram.getSize());
    }

    @Test
    public void testOrderAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("c", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals("a", diagram.getX(0));
        assertEquals("b", diagram.getX(1));
        assertEquals("c", diagram.getX(2));
    }

    @Test
    public void testFreqAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("c", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals(1, (int) diagram.getY(0));
        assertEquals(1, (int) diagram.getY(1));
        assertEquals(1, (int) diagram.getY(2));
    }


    @Test
    public void testSizeCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("a", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals(2, diagram.getSize());
    }

    @Test
    public void testOrderCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("a", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals("a", diagram.getX(0));
        assertEquals("b", diagram.getX(1));
    }

    @Test
    public void testFreqCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null);
        Call c2 = new Call("b", null);
        Call c3 = new Call("a", null);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        CallHistogram diagram = new CallHistogram(data);
        assertEquals(2, diagram.getY(0));
        assertEquals(1, diagram.getY(1));
    }


    @Test
    public void testFindPosition3To0() {
        CallHistogram diagram = new CallHistogram(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{2, 2, 2, 3}));
        assertEquals(0, diagram.findNewPosition(3, data));
    }

    @Test
    public void testFindPosition4To2() {
        CallHistogram diagram = new CallHistogram(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{5, 4, 2, 2, 3, 1}));
        assertEquals(2, diagram.findNewPosition(4, data));
    }

    @Test
    public void testFindPosition3To3() {
        CallHistogram diagram = new CallHistogram(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{3, 3, 3, 3, 2}));
        assertEquals(3, diagram.findNewPosition(3, data));
    }

    @Test
    public void testFindPosition3To1() {
        CallHistogram diagram = new CallHistogram(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{3, 2, 2, 3, 1}));
        assertEquals(1, diagram.findNewPosition(3, data));
    }

    @Test
    public void findSmallerThanTheLast() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(5, diagram.findSmallerThan(1, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanTheLastRepeating() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(8, diagram.findSmallerThan(1, new Integer[]{3, 2, 2, 2, 1, 1, 1, 1}));
    }

    @Test
    public void findSmallerThanNothing() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(4, diagram.findSmallerThan(1, new Integer[]{10, 7, 4, 3}));
    }

    @Test
    public void findSmallerThanMiddle() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(4, diagram.findSmallerThan(2, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanAll() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(0, diagram.findSmallerThan(10, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanAllDifferent() throws Exception {
        CallHistogram diagram = new CallHistogram(null);
        assertEquals(5, diagram.findSmallerThan(5, new Integer[]{10, 9, 8, 7, 6, 4, 3, 2, 1}));
    }



}