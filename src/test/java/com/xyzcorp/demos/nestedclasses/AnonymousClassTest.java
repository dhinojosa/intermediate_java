package com.xyzcorp.demos.nestedclasses;

import org.junit.jupiter.api.Test;

public class AnonymousClassTest {
    @Test
    void testAnonymousClass() {
         final String commonName = "Doug";
         final int x = 30;
         HelloWorld olaMundoPortuguês = new HelloWorld() {
             int x = 90;

             @Override
             public void greet() {
                 System.out.println("Ola!");
             }

             @Override
             public void greetSomeone(String someone) {
                 System.out.println("Ola! " + someone + ", have you met " + commonName + " favorite number is: " + x);
             }
         };

         olaMundoPortuguês.greetSomeone("Peterson");

        HelloWorld holaMundoEspañol = new HelloWorld() {
            @Override
            public void greet() {
                System.out.println("Hola!");
            }

            @Override
            public void greetSomeone(String someone) {
                System.out.println("Hola! " + someone);
            }
        };

        holaMundoEspañol.greetSomeone("Eduardo");
    }

    @Test
    void testAnonymousClassWithOneAbstractMethod() {
        CombineTwoThings<String> combineTwoThings =
            (first, last) -> first + " ~ " + last;

    }
}
