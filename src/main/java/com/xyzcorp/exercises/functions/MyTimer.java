package com.xyzcorp.exercises.functions;

import java.sql.Time;
import java.util.function.Supplier;

public class MyTimer {
    public static <T> TimeResult<T> measureTime(Supplier<T> supplier) {
        long startTime = System.currentTimeMillis();
        T result = supplier.get();
        long endTime = System.currentTimeMillis();
        return new TimeResult<T>(result, endTime - startTime);
    }
}
