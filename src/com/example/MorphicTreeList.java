package com.example;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Nate on 5/19/15.
 *
 * This class implements a data structure that can be either a binary tree or a bi-directional linked list.
 * It can also convert between the two at will.
 */
public class MorphicTreeList {
    private Mode mode;
    private BiNode root;
    private Random branchSeed = new Random();

    private void treeInsert(int val) {
        BiNode nodeToInsert = new BiNode(val);
        BiNode start = root;

        while(start != null) {
            if(val < start.data) {
                if(start.node1 != null) {
                    start = start.node1;
                } else {
                    start.node1 = nodeToInsert;
                    break;
                }
            } else if(val > start.data) {
                if(start.node2 != null) {
                    start = start.node2;
                } else {
                    start.node2 = nodeToInsert;
                    break;
                }
            } else if(val == start.data) {
                if(start.node2 != null && start.node1 != null) {
                    start = branchSeed.nextBoolean() ? start.node1 : start.node2;
                } else if(start.node2 == null && start.node1 != null) {
                    start.node2 = nodeToInsert;
                    break;
                } else if(start.node2 != null && start.node1 == null) {
                    start.node1 = nodeToInsert;
                    break;
                } else {
                    if(branchSeed.nextBoolean()) {
                        start.node1 = nodeToInsert;
                        break;
                    } else {
                        start.node2 = nodeToInsert;
                        break;
                    }
                }
            }
        }
    }

    private ArrayList<Integer> treeScan(BiNode node) {
        ArrayList<Integer> leftList = new ArrayList<>();
        ArrayList<Integer> rightList = new ArrayList<>();
        ArrayList<Integer> returnList;

        if(node.node1 != null) {
            leftList = treeScan(node.node1);
        }

        if(node.node2 != null) {
            rightList = treeScan(node.node2);
        }

        returnList = new ArrayList<>();
        returnList.addAll(leftList);
        returnList.add(node.data);
        returnList.addAll(rightList);
        return returnList;
    }


    public MorphicTreeList() {
        mode = Mode.TREE; // Default to Tree mode
    }

    public MorphicTreeList(String mode) {
        this.mode = Mode.valueOf(mode);
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
            root = new BiNode(val);
        } else {
            treeInsert(val);
        }
    }

    public ArrayList<Integer> values() {
        return treeScan(root);
    }
}
