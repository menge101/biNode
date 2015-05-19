package com.example;

import org.junit.Test;

import java.util.ArrayList;

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
        assertEquals(mt.getMode(), "TREE");
    }

    @Test
    public void test_AddNode() {
        mt = new MorphicTreeList();
        mt.add(5);
        Integer[] expected = new Integer[]{5};
        ArrayList<Integer> actual = mt.values();
        Integer[] actualArray = actual.toArray(new Integer[actual.size()]);
        assertArrayEquals(expected, actualArray);
    }
}