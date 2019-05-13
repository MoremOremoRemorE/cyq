package com.cyq.cyq.utils;

import java.util.UUID;

public class SystemUtils {

    public static String getUUID() {
        String uuid = UUID.randomUUID().toString();
        uuid = uuid.replace("-", "");
        return uuid;
    }
}
