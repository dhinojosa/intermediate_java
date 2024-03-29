package com.xyzcorp.demos.designpatterns.singleton;

import com.xyzcorp.demos.threads.MySingleton;
import org.junit.jupiter.api.Test;

public class SingletonTest {

    @Test
    public void useSingleton() throws Exception {

        MySingleton instance = MySingleton.getInstance();
        MySingleton instance2 = MySingleton.getInstance();

        boolean b = instance == instance2;
        System.out.println("b = " + b);
    }
}
