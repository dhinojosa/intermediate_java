package com.xyzcorp;

//given a x returns true or false
public interface MyPredicate<T> {
    public boolean test(T item);
}
