package com.codedifferentlty.datastructures.btree;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class NodeTest {

    private Node _a;
    private Node _d;
    private Node _f;
    private Node _h;
    private Node _i;
    private Node _k;
    private Node _l;
    private Node _m;
    private Node _p;

    @Before
    public void setUp(){
        _a = new Node("A");
        _h = new Node("H");
        _k = new Node("K");
        _p = new Node("P");
        _f = new Node("F", null, _h);
        _m = new Node("M", null, _p);
        _d = new Node("D", _a, _f);
        _l = new Node("L", _k, _m);
        _i = new Node("I", _d, _l);
    }

    @Test
    public void testMinimum() {
        assertSame(_a, _a.minimum());
        assertSame(_a, _d.minimum());
        assertSame(_f, _f.minimum());
        assertSame(_h, _h.minimum());
        assertSame(_a, _i.minimum());
        assertSame(_k, _k.minimum());
        assertSame(_k, _l.minimum());
        assertSame(_m, _m.minimum());
        assertSame(_p, _p.minimum());
    }

    @Test
    public void testMaximum() {
        assertSame(_a, _a.maximum());
        assertSame(_h, _d.maximum());
        assertSame(_h, _f.maximum());
        assertSame(_h, _h.maximum());
        assertSame(_p, _i.maximum());
        assertSame(_k, _k.maximum());
        assertSame(_p, _l.maximum());
        assertSame(_p, _m.maximum());
        assertSame(_p, _p.maximum());
    }

    @Test
    public void testSuccessor() {
        assertSame(_d, _a.successor());
        assertSame(_f, _d.successor());
        assertSame(_h, _f.successor());
        assertSame(_i, _h.successor());
        assertSame(_k, _i.successor());
        assertSame(_l, _k.successor());
        assertSame(_m, _l.successor());
        assertSame(_p, _m.successor());
        assertNull(_p.successor());
    }

    @Test
    public void testPredecessor() {
        assertNull(_a.predecessor());
        assertSame(_a, _d.predecessor());
        assertSame(_d, _f.predecessor());
        assertSame(_f, _h.predecessor());
        assertSame(_h, _i.predecessor());
        assertSame(_i, _k.predecessor());
        assertSame(_k, _l.predecessor());
        assertSame(_l, _m.predecessor());
        assertSame(_m, _p.predecessor());
    }

    @Test
    public void testIsSmaller() {
        assertTrue(_a.isSmaller());
        assertTrue(_d.isSmaller());
        assertFalse(_f.isSmaller());
        assertFalse(_h.isSmaller());
        assertFalse(_i.isSmaller());
        assertTrue(_k.isSmaller());
        assertFalse(_l.isSmaller());
        assertFalse(_m.isSmaller());
        assertFalse(_p.isSmaller());
    }

    @Test
    public void testIsLarger() {
        assertFalse(_a.isLarger());
        assertFalse(_d.isLarger());
        assertTrue(_f.isLarger());
        assertTrue(_h.isLarger());
        assertFalse(_i.isLarger());
        assertFalse(_k.isLarger());
        assertTrue(_l.isLarger());
        assertTrue(_m.isLarger());
        assertTrue(_p.isLarger());
    }

    @Test
    public void testSize() {
        assertEquals(1, _a.size());
        assertEquals(4, _d.size());
        assertEquals(2, _f.size());
        assertEquals(1, _h.size());
        assertEquals(9, _i.size());
        assertEquals(1, _k.size());
        assertEquals(4, _l.size());
        assertEquals(2, _m.size());
        assertEquals(1, _p.size());
    }

    @Test
    public void testEquals() {
        Node a = new Node("A");
        Node h = new Node("H");
        Node k = new Node("K");
        Node p = new Node("P");
        Node f = new Node("F", null, h);
        Node m = new Node("M", null, p);
        Node d = new Node("D", a, f);
        Node l = new Node("L", k, m);
        Node i = new Node("I", d, l);
        assertEquals(a, _a);
        assertEquals(d, _d);
        assertEquals(f, _f);
        assertEquals(h, _h);
        assertEquals(i, _i);
        assertEquals(k, _k);
        assertEquals(l, _l);
        assertEquals(m, _m);
        assertEquals(p, _p);
        assertFalse(_i.equals(null));
        assertFalse(_f.equals(_d));
    }
}
