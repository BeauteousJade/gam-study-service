package com.pby.gamstudy.util;

import java.util.UUID;

public class StringUtil {
    public static String generateId() {
        return UUID.randomUUID().toString();
    }
}
