package com.training.aerospike.repo;

import com.google.common.base.CaseFormat;

public class Utils {
    public static String getSetName(Class clazz) {
        if (clazz == null) {
            throw new IllegalArgumentException("clazz");
        }
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.LOWER_UNDERSCORE, clazz.getSimpleName());
    }
}
