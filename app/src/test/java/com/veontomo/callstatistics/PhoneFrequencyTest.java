package com.veontomo.callstatistics;

import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * Tests for methods of {@link PhoneFrequency} class.
 *
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
        assertEquals(2, (int) diagram.getY(0));
        assertEquals(1, (int) diagram.getY(1));
    }



    @Test
    public void testFindPosition3To0(){
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Float> data = new ArrayList<>(Arrays.asList(new Float[]{2f, 2f, 2f, 3f}));
        assertEquals(0, diagram.findNewPosition(3, data));
    }

    @Test
    public void testFindPosition4To2(){
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Float> data = new ArrayList<>(Arrays.asList(new Float[]{5f, 4f, 2f, 2f, 3f, 1f}));
        assertEquals(2, diagram.findNewPosition(4, data));
    }

    @Test
    public void testFindPosition3To3(){
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Float> data = new ArrayList<>(Arrays.asList(new Float[]{3f, 3f, 3f, 3f, 2f}));
        assertEquals(3, diagram.findNewPosition(3, data));
    }
    @Test
    public void testFindPosition3To1(){
        PhoneFrequency diagram = new PhoneFrequency(null);
        List<Float> data = new ArrayList<>(Arrays.asList(new Float[]{3f, 2f, 2f, 3f, 1f}));
        assertEquals(1, diagram.findNewPosition(3, data));
    }
}