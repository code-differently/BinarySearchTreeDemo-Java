package com.codedifferentlty.datastructures.btree;

public class Node implements Cloneable{
    /**
     * Each node holds a value, a reference to a parent,
     * a smaller (or left) child, and a larger (or right) child
     */
    private Object _value;
    private Node _parent;
    private Node _smaller;
    private Node _larger;

    /**
     * You’ve also provided two constructors. The first constructor is for creating leaf nodes—those with no
     * children—so its only argument is a value:
     */
    public Node(Object value) {
        this(value, null, null);
    }

    /**
     * The second constructor, however, is somewhat of a convenience, enabling you to create nodes that have
     * children. Notice that if you specify a non-null child, the constructor conveniently sets that child’s parent.
     * This, as you may recall from the tests, makes it trivial to wire nodes together into a tree structure:
     */
    public Node(Object value, Node smaller, Node larger) {
        setValue(value);
        setSmaller(smaller);
        setLarger(larger);
        if (smaller != null) {
            smaller.setParent(this);
        }
        if (larger != null) {
            larger.setParent(this);
        }
    }

    /**
     * Once constructed, you need access to the node’s value, its parent, and any of its children. For this, you
     * create some standard getters and setters. Nothing too strange there except that you’ve put in a few extra
     * assertions—for example, checking to make sure that you haven’t set both children to the same node:
     */
    public Object getValue() {
        return _value;
    }

    public void setValue(Object value) {
        assert value != null : "value can’t be null";
        _value = value;
    }
    public Node getParent() {
        return _parent;
    }

    public void setParent(Node parent) {
        _parent = parent;
    }
    public Node getSmaller() {
        return _smaller;
    }
    public void setSmaller(Node smaller) {
        _smaller = smaller;
    }
    public Node getLarger() {
        return _larger;
    }
    public void setLarger(Node larger) {
        _larger = larger;
    }

    /**
     * Next, follow some convenience methods for determining various characteristics of each node.
     * The methods isSmaller() and isLarger() return true only if the node is the smaller or larger child
     * of its parent, respectively:
     */
    public boolean isSmaller() {
        return getParent() != null && this == getParent().getSmaller();
    }
    public boolean isLarger() {
        return getParent() != null && this == getParent().getLarger();
    }

    /**
     * Finding the minimum or maximum is not much more complex. Recall that the minimum of a node is
     * its smallest child, and the maximum is its largest (or itself if it has no children). Notice that the code
     * for maximum() is almost identical to that of minimum(); whereas minimum() calls getSmaller(),
     * maximum() calls getLarger():
     */

    public Node minimum() {
        Node node = this;
        while (node.getSmaller() != null) {
            node = node.getSmaller();
        }
        return node;
    }

    public Node maximum() {
        Node node = this;
        while (node.getLarger() != null) {
            node = node.getLarger();
        }
        return node;
    }

    /**
     * Looking at successor(), you can see that if the node has a larger child, then you take its minimum. If
     * not, you start moving up the tree looking for the “right-hand” turn by checking whether the current
     * node is the larger of its parent’s children. If it is the larger, then it must be to the right of its parent, and
     * you would be moving to the left back up the tree.
     */
    public Node successor() {
        if (getLarger() != null) {
            return getLarger().minimum();
        }
        Node node = this;
        while (node.isLarger()) {
            node = node.getParent();
        }
        return node.getParent();
    }

    public Node predecessor() {
        if (getSmaller() != null) {
            return getSmaller().maximum();
        }
        Node node = this;
        while (node.isSmaller()) {
            node = node.getParent();
        }
        return node.getParent();
    }
    public int size() {
        return size(this);
    }

    /**
     * Finally, we have equals(). This method is a node’s most complex (though still fairly straightforward),
     * but it will be used extensively later to check the structure of the trees created by the BinarySearchTree
     * class.
     */
    public boolean equals(Object object) {
        if (this == object) {
            return true;
        }
        if (object == null || object.getClass() != getClass()) {
            return false;
        }
        Node other = (Node) object;
        return getValue().equals(other.getValue())
                && equalsSmaller(other.getSmaller())
                && equalsLarger(other.getLarger());
    }

    private int size(Node node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.getSmaller()) + size(node.getLarger());
    }
    private boolean equalsSmaller(Node other) {
        return getSmaller() == null && other == null
                || getSmaller() != null && getSmaller().equals(other);
    }
    private boolean equalsLarger(Node other) {
        return getLarger() == null && other == null
                || getLarger() != null && getLarger().equals(other);
    }


}
