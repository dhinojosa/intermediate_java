package com.xyzcorp.exercises.functions;

import java.sql.Time;
import java.util.function.Supplier;

public class MyTimer {
    public static TimeResult measureTime(Supplier<Integer> integerSupplier) {

        //startTime
        Integer integer = integerSupplier.get();
        //endTime
        return new TimeResult(...);
    }
}
