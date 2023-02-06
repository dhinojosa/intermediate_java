package com.xyzcorp.demos.annotations.basic;

import java.util.Arrays;

public class AnnotationSeeker {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader
            .loadClass("com.xyzcorp.demos.annotations.basic.MinesweeperGame");
        Arrays.stream(aClass.getAnnotations()).forEach(System.out::println);
    }
}
