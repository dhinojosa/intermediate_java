package com.xyzcorp.demos.threads;

public class MySingleton {
    private static MySingleton instance = new MySingleton();
    private MySingleton() { }
    public static MySingleton getInstance() {
        return instance;
    }
}
