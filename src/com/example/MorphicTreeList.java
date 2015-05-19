package com.example;

import java.util.ArrayList;

/**
 * Created by Nate on 5/19/15.
 *
 * This class implements a data structure that can be either a binary tree or a bi-directional linked list.
 * It can also convert between the two at will.
 */
public class MorphicTreeList {
    private Mode mode;
    private BiNode root;

    public MorphicTreeList() {
        mode = Mode.TREE; // Default to Tree mode
    }

    public MorphicTreeList(Mode mode) {
        this.mode = mode;
    }

    public enum Mode {
        TREE, LIST
    }

    public String getMode() {
        return mode.name();
    }

    public boolean isTree() {
        return this.getMode().equals("TREE");
    }

    public boolean isList() {
        return this.getMode().equals("LIST");
    }

    public void add(int val) {
        // create the root if it does not yet exist
        if(root == null) {
            root = new BiNode();
            root.data = val;
        }
    }

    public ArrayList<Integer> values() {
        ArrayList<Integer> returnList = new ArrayList<>();
        returnList.add(root.data);
        return returnList;
    }
}
