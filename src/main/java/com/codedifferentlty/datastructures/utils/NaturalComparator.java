package com.codedifferentlty.datastructures.utils;

import java.util.Comparator;

public final class NaturalComparator implements Comparator {
    public static final NaturalComparator INSTANCE = new NaturalComparator();

    @Override
    public int compare(Object left, Object right) {
        return ((Comparable) left).compareTo(right);
    }
}
