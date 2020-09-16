package com.codedifferentlty.datastructures.utils;

import java.util.Comparator;

public final class CallCountingComparator implements Comparator {
    private final Comparator _comparator;
    private int _callCount;
    public CallCountingComparator(Comparator comparator) {
        assert comparator != null : "comparator cannot be null";
        _comparator = comparator;
        _callCount = 0;
    }
    public int compare(Object left, Object right) {
        ++_callCount;
        return _comparator.compare(left, right);
    }
    public int getCallCount() {
        return _callCount;
    }
}
