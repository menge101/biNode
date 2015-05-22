package com.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;

/**
 * Created by Nate on 5/19/15.
 *
 * This is the test class for MorphicTreeList.
 */
public class MorphicTreeListTest {
    MorphicTreeList mt;

    @Test
    public void test_emptyConstructor() {
        mt = new MorphicTreeList();
        assertEquals(mt.isTree(), true);
        assertEquals(mt.isList(), false);
        assertEquals(mt.getMode(), "TREE");
    }

    @Test
    public void test_modeConstructor() {
        mt = new MorphicTreeList("LIST");
        assertEquals(mt.isTree(), false);
        assertEquals(mt.isList(), true);
        assertEquals(mt.getMode(), "LIST");
        mt = new MorphicTreeList("TREE");
        assertEquals(mt.isTree(), true);
        assertEquals(mt.isList(), false);
        assertEquals(mt.getMode(), "TREE");
    }

    @Test
    public void test_AddNodeTreeMode() {
        mt = new MorphicTreeList();
        mt.add(5);
        Integer[] expected = new Integer[]{5};
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(expected, actualArray);
    }

    @Test
    public void test_Add2ndNodeTreeMode() {
        mt = new MorphicTreeList();
        mt.add(5);
        mt.add(4);
        Integer[] expected = new Integer[]{4,5};
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(expected, actualArray);
    }

    @Test
    public void test_AddMoreNodesTreeMode() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{4,5,2,8,1,9,2,6,7,1,8,3};
        for(int i: values) {
            mt.add(i);
        }
        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }

    @Test
    public void test_AddNodeListMode() {
        mt = new MorphicTreeList("LIST");
        mt.add(5);
        Integer[] expected = new Integer[]{5};
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(expected, actualArray);
    }

    @Test
    public void test_Add2ndNodeListMode() {
        mt = new MorphicTreeList();
        mt.add(5);
        mt.add(4);
        Integer[] expected = new Integer[]{4,5};
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(expected, actualArray);
    }

    @Test
    public void test_AddMoreNodesListMode() {
        mt = new MorphicTreeList("LIST");
        Integer[] values = new Integer[]{4,5,2,8,1,9,2,6,7,1,8,3};
        for(int i: values) {
            mt.add(i);
        }
        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }
}