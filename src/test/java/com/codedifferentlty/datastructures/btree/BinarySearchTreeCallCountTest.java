package com.codedifferentlty.datastructures.btree;

import com.codedifferentlty.datastructures.utils.CallCountingComparator;
import com.codedifferentlty.datastructures.utils.NaturalComparator;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class BinarySearchTreeCallCountTest {
    private static final int TEST_SIZE = 1000;
    private CallCountingComparator _comparator;
    private BinarySearchTree _tree;

    @Before
    public void setUp() {
        _comparator = new CallCountingComparator(NaturalComparator.INSTANCE);
        _tree = new BinarySearchTree(_comparator);
    }

    @Test
    public void testRandomInsertion() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            _tree.insert((int) (Math.random() * TEST_SIZE));
        }
        reportCalls("testRandomInsertion");
    }

    @Test
    public void testInOrderInsertion() {
        for (int i = 0; i < TEST_SIZE; ++i) {
            _tree.insert(i);
        }
        reportCalls("testInOrderInsertion");
    }

    @Test
    public void testPreOrderInsertion() {
        List list = new ArrayList(TEST_SIZE);
        for (int i = 0; i < TEST_SIZE; ++i) {
            list.add(i);
        }
        preOrderInsert(list, 0, list.size() - 1);
        reportCalls("testPreOrderInsertion");
    }

    private void preOrderInsert(List list, int lowerIndex, int upperIndex) {
        if (lowerIndex > upperIndex) {
            return;
        }
        int index = lowerIndex + (upperIndex - lowerIndex) / 2;
        _tree.insert(list.get(index));
        preOrderInsert(list, lowerIndex, index - 1);
        preOrderInsert(list, index + 1, upperIndex);
    }

    private void reportCalls(String name) {
        System.out.println(name + " : " + _comparator.getCallCount() + " calls");
    }

}
