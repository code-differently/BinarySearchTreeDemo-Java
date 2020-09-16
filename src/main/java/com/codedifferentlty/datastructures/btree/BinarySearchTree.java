package com.codedifferentlty.datastructures.btree;

import java.util.Comparator;

/**
 * The BinarySearchTree class holds a comparator to use for comparing values; the root node, which
 * may be null if the tree is empty; and a method for providing access to the root node that you used in
 * your tests. Notice that you haven’t implemented any interface, nor have you extended any base class.
 * This binary search tree implementation is not really intended for use in its present form.
 */
public class BinarySearchTree {
    private final Comparator _comparator;
    private Node _root;

    public BinarySearchTree(Comparator comparator) {
        assert comparator != null : "comparator can’t be null";
        _comparator = comparator;
    }

    /**
     * The simplest method you implemented was search(). This method looks for a value in the tree and
     * returns the corresponding node, or null if the value wasn’t found. It starts at the root node and continues
     * until it either finds a match or runs out of nodes. At each pass through the while loop, the search value is
     * compared with the value held in the current node. If the values are equal, you’ve found the node you’re
     * looking for and can exit the loop; otherwise, you follow the smaller or larger link as appropriate:
     */
    public Node search(Object value) {
        assert value != null : "value can’t be null";
        Node node = _root;
        while (node != null) {
            int cmp = _comparator.compare(value, node.getValue());
            if (cmp == 0) {
                break;
            }
            node = cmp < 0 ? node.getSmaller() : node.getLarger();
        }
        return node;
    }

    public Node insert(Object value) {
        Node parent = null;
        Node node = _root;
        int cmp = 0;
        while (node != null) {
            parent = node;
            cmp = _comparator.compare(value, node.getValue());
            node = cmp <= 0 ? node.getSmaller() : node.getLarger();
        }
        Node inserted = new Node(value);
        inserted.setParent(parent);
        if (parent == null) {
            _root = inserted;
        } else if (cmp < 0) {
            parent.setSmaller(inserted);
        } else {
            parent.setLarger(inserted);
        }
        return inserted;
    }

    public Node delete(Object value) {
        Node node = search(value);
        if (node == null) {
            return null;
        }
        Node deleted = node.getSmaller() != null && node.getLarger() != null ?
                node.successor() : node;
        assert deleted != null : "deleted can’t be null";
        Node replacement = deleted.getSmaller() != null ? deleted.getSmaller() :
                deleted.getLarger();
        if (replacement != null) {
            replacement.setParent(deleted.getParent());
        }
        if (deleted == _root) {
            _root = replacement;
        } else if (deleted.isSmaller()) {
            deleted.getParent().setSmaller(replacement);
        } else {
            deleted.getParent().setLarger(replacement);
        }
        if (deleted != node) {
            Object deletedValue = node.getValue();
            node.setValue(deleted.getValue());
            deleted.setValue(deletedValue);
        }
        return deleted;
    }

    public Node getRoot() {
        return _root;
    }

}
