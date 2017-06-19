package com.xyzcorp;

import java.util.ArrayList;
import java.util.List;

public class Functions {

    public static <T> List<T> myFilter(List<T> list, MyPredicate<T> myPredicate) {
        ArrayList<T> result = new ArrayList<>();
        for (T t: list) {
            if (myPredicate.test(t)) result.add(t);
        }
        return result;
    }

    public static <T, R> List<R> myMap(List<T> list, MyFunction<T, R> myFunction) {
        ArrayList<R> result = new ArrayList<>();
        for (T t: list) {
            result.add(myFunction.apply(t));
        }
        return result;
    }

    public static <T> void myForEach(List<T> list, MyConsumer<T> myConsumer) {

    }
}










