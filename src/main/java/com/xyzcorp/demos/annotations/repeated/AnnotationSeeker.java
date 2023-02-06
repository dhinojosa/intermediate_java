package com.xyzcorp.demos.annotations.repeated;

import java.util.Arrays;

public class AnnotationSeeker {
    public static void main(String[] args) throws ClassNotFoundException {
        ClassLoader systemClassLoader = ClassLoader.getSystemClassLoader();
        Class<?> aClass = systemClassLoader
            .loadClass("com.xyzcorp.demos.annotations.repeated.MyRunnable");
        Arrays.stream(aClass.getAnnotations()).forEach(System.out::println);
    }
}
