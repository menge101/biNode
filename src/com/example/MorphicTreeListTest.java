package com.example;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

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

    @Test
    public void test_emptyTreeMorph2List()  {
        mt = new MorphicTreeList();
        mt.morph();
        assertEquals(mt.getMode(), "LIST");
    }

    @Test
    public void test_singleNodeMorph2List() {
        mt = new MorphicTreeList();
        mt.add(5);
        mt.morph();
        assertEquals(mt.getMode(), "LIST");
    }

    @Test
    public void test_twoNodeLeftMorph2List() {
        mt = new MorphicTreeList();
        mt.add(5);
        mt.add(2);
        mt.morph();
        assertEquals(mt.getMode(), "LIST");
    }

    @Test
    public void test_simpleMorph2list() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{4,5,2};
        for(int i: values) {
            mt.add(i);
        }
        BiNode left, right, root;
        root = mt.getRoot();
        left = root.node1;
        right = root.node2;

        mt.morph();

        assertNull(left.node1);
        assertTrue(left.node2 == root);
        assertTrue(root.node1 == left);
        assertTrue(root.node2 == right);
        assertTrue(right.node1 == root);
        assertNull(right.node2);
    }

    @Test
    public void test_emptyLeftNodeMorph2LIst() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{5,7};
        for(int i: values) {
            mt.add(i);
        }

        mt.morph();

        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }

    @Test
    public void test_fullerTreeMorph2List() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{10,5,15,2,7,13,17};
        for(int i: values) {
            mt.add(i);
        }

        mt.morph();

        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }

    @Test
    public void test_lessComplexMorph2List() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{4,5,2,8};
        for(int i: values) {
            mt.add(i);
        }

        mt.morph();
        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }

    @Test
    public void test_complexMorph2list() {
        mt = new MorphicTreeList();
        Integer[] values = new Integer[]{4,5,2,8,1,9,2,6,7,1,8,3};
        for(int i: values) {
            mt.add(i);
        }

        mt.morph();

        Arrays.sort(values);
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(values, actualArray);
    }
}