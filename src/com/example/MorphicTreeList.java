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
    private int nodeCount = 0;

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

    // In list mode, node1 is the next node and node2 is the previous node
    private void listInsert(int val) {
        BiNode currentNode = root;
        BiNode node2Insert = new BiNode(val);

        //currentNode will be the node that node2Insert should preceed
        //if the value is greater than or equal
        //if value is less than it should preceed it
        while(currentNode.data < val && currentNode.node2 != null) {
            currentNode = currentNode.node2;
        }

        if(currentNode.node1 == null && currentNode.data >= node2Insert.data) {
            root = node2Insert;
            node2Insert.node2 = currentNode;
            currentNode.node1 = node2Insert;
        } else if(currentNode.node2 == null) {
            currentNode.node2 = node2Insert;
            node2Insert.node1 = currentNode;
        } else {
            node2Insert.node2 = currentNode;
            node2Insert.node1 = currentNode.node1;
            currentNode.node1.node2 = node2Insert;
            currentNode.node1 = node2Insert;
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

    private ArrayList<Integer> listScan(BiNode node) {
        ArrayList<Integer> returnList = new ArrayList<>();

        while(node != null) {
            returnList.add(node.data);
            node = node.node2;
        }
        return returnList;
    }

    // These three following methods should only be used on linked lists
    private static BiNode lastNode(BiNode start) {
        while(start.node2 != null) { start = start.node2; }
        return start;
    }

    private static BiNode firstNode(BiNode start) {
        while(start.node1 != null) { start = start.node1; }
        return start;
    }

    private static BiNode listCenter(BiNode start, int listSize) {
        int centerNode = (listSize + 1) / 2;
        BiNode currentNode = start;
        for(int i=1; i < centerNode; i++) {
            currentNode = currentNode.node2;
        }
        return currentNode;
    }

    private static boolean isOdd(int number) {
        return (number % 2 != 0);
    }

    private BiNode listMorph(BiNode start) {
        // handle empty and single node cases
        if(start == null) { return start; }
        else if(start.node1 == null && start.node2 == null) { return start; }
        //lift the leaves up, return the head of the list
        if(start.node1 != null) {
            //set tail of returned list as precedeing node
            BiNode precedingNode = MorphicTreeList.lastNode(listMorph(start.node1));
            precedingNode.node2 = start;
            start.node1 = precedingNode;
        }
        if(start.node2 != null) {
            //set head of returned list as following node
            BiNode followingNode = listMorph(start.node2);
            followingNode.node1 = start;
            start.node2 = followingNode;
        }
        return MorphicTreeList.firstNode(start);
    }

    private BiNode treeMorph(BiNode start, int listSize) {
        if(start == null) { return start; }
        if(start.node1 == null && start.node2 == null) { return start; }
        // select center node of list
        BiNode centerNode = MorphicTreeList.listCenter(start, listSize);
        //break list into components
        int leftSize;
        int rightSize = listSize / 2;
        if(MorphicTreeList.isOdd(listSize)) {
            leftSize = rightSize;
        } else {
            leftSize = rightSize - 1;
        }

        //drop neighbors, return root of tree
        BiNode rightOfCenter = centerNode.node2;
        if(centerNode.node1 != null) {
            centerNode.node1.node2 = null;
        }
        if(centerNode.node2 != null) {
            centerNode.node2.node1 = null;
        }
        centerNode.node1 = null;
        centerNode.node2 = null;
        if(leftSize > 0) {
            centerNode.node1 = treeMorph(start, leftSize);
        }
        if(rightSize > 0) {
            centerNode.node2 = treeMorph(rightOfCenter, rightSize);
        }
        return centerNode;
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
            if(isTree()) {
                treeInsert(val);
            } else if(isList()) {
                listInsert(val);
            }

        }
        nodeCount++;
    }

    public ArrayList<Integer> values() {
        if(isTree()) { return treeScan(root); }
        else { return listScan(root); }
    }

    public BiNode getRoot() {
        return root;
    }

    public void morph() {
        if(isTree()) {
            root = listMorph(root);
            mode = Mode.LIST;
        } else {
            root = treeMorph(root, nodeCount);
            mode = Mode.TREE;
        }
    }
}
