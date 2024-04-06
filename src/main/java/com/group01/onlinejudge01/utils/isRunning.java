package com.group01.onlinejudge01.utils;

import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class isRunning {
    private static Set<String> set = new ConcurrentSkipListSet<>();

    public static boolean contain(String submitId) {
        return set.contains(submitId);
    }

    public static void add(String submitId) {
        set.add(submitId);
    }

    public static void del(String submitId) {
        set.remove(submitId);
    }
}
