package com.pby.gamstudy.util;

import java.util.Collection;

public class ArraysUtil {

    public static boolean isEmpty(Collection<?> collection) {
        return collection == null || collection.isEmpty();
    }

    public static <T> boolean isEmpty(T... ts) {
        return ts == null || ts.length == 0;
    }
}
