package com.veontomo.callstatistics;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * Tests for methods of {@link PhoneFrequency} class.
 */
public class PhoneFrequencyTest {

    @Test
    public void testEmptyData() {
        PhoneFrequency diagram = new PhoneFrequency(new ArrayList<Call>());
        assertEquals(0, diagram.getSize());
    }

    @Test
    public void testSizeAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("c", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals(3, diagram.getSize());
    }

    @Test
    public void testOrderAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("c", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals("a", diagram.getX(0));
        assertEquals("b", diagram.getX(1));
        assertEquals("c", diagram.getX(2));
    }

    @Test
    public void testFreqAllDifferentPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("c", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals(1, (int) diagram.getY(0));
        assertEquals(1, (int) diagram.getY(1));
        assertEquals(1, (int) diagram.getY(2));
    }


    @Test
    public void testSizeCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("a", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals(2, diagram.getSize());
    }

    @Test
    public void testOrderCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("a", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals("a", diagram.getX(0));
        assertEquals("b", diagram.getX(1));
    }

    @Test
    public void testFreqCoincidingPhoneNumbers() {
        Call c1 = new Call("a", null, 1, 2, 3l);
        Call c2 = new Call("b", null, 1, 2, 3l);
        Call c3 = new Call("a", null, 1, 2, 3l);
        List<Call> data = new ArrayList<>();
        data.add(c1);
        data.add(c2);
        data.add(c3);
        PhoneFrequency diagram = new PhoneFrequency(data);
        assertEquals(2, diagram.getY(0));
        assertEquals(1, diagram.getY(1));
    }


    @Test
    public void testFindPosition3To0() {
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{2, 2, 2, 3}));
        assertEquals(0, diagram.findNewPosition(3, data));
    }

    @Test
    public void testFindPosition4To2() {
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{5, 4, 2, 2, 3, 1}));
        assertEquals(2, diagram.findNewPosition(4, data));
    }

    @Test
    public void testFindPosition3To3() {
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{3, 3, 3, 3, 2}));
        assertEquals(3, diagram.findNewPosition(3, data));
    }

    @Test
    public void testFindPosition3To1() {
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Integer> data = new ArrayList<>(Arrays.asList(new Integer[]{3, 2, 2, 3, 1}));
        assertEquals(1, diagram.findNewPosition(3, data));
    }

    @Test
    public void findSmallerThanTheLast() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(5, diagram.findSmallerThan(1, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanTheLastRepeating() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(8, diagram.findSmallerThan(1, new Integer[]{3, 2, 2, 2, 1, 1, 1, 1}));
    }

    @Test
    public void findSmallerThanNothing() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(4, diagram.findSmallerThan(1, new Integer[]{10, 7, 4, 3}));
    }

    @Test
    public void findSmallerThanMiddle() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(4, diagram.findSmallerThan(2, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanAll() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(0, diagram.findSmallerThan(10, new Integer[]{3, 2, 2, 2, 1}));
    }

    @Test
    public void findSmallerThanAllDifferent() throws Exception {
        PhoneFrequency diagram = new PhoneFrequency(null);
        assertEquals(5, diagram.findSmallerThan(5, new Integer[]{10, 9, 8, 7, 6, 4, 3, 2, 1}));
    }



}