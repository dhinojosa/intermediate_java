package com.xyzcorp.demos.exceptions;

import org.assertj.core.api.ThrowableAssert;
import org.junit.jupiter.api.*;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.stream.BaseStream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ExceptionThrowerTest {

    @BeforeEach
    void init() {
        System.out.println("Before each test");
    }

    @BeforeAll
    static void beforeAll() {
        System.out.println("Before all the tests");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("After the all tests have run");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Before each class");
    }

    /**
     * Warning to the end user, this will happen, take care of it
     */
    @Test
    void testCheckedExceptionHandling() {
        try {
            ExceptionThrower.throwCheckedException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * You should've known better
     */
    @Test
    void testRuntimeExceptionHandling() {
        try {
            ExceptionThrower.throwRuntimeException();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    void testRuntimeExceptionHandlingWithAssertJ() {
        assertThatThrownBy(ExceptionThrower::throwRuntimeException)
            .isInstanceOf(RuntimeException.class)
            .hasMessage("This is a runtime exception");
    }

    @Test
    void testCustomExceptionHandlingWithAssertJ() {
        assertThatThrownBy(ExceptionThrower::throwCustomException)
            .isInstanceOf(CustomException.class)
            .hasMessage("Yay! I create my own");
    }

    @Test
    void testMakingACallWithJavaIO() {
        PrintWriter out = null;
        try {
            URL url = new URL("https://java.net");
            out = new PrintWriter(new FileWriter("OutFile.txt"));
            System.out.println(10 / 0);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    @Test
    void testAutoClose() {
        ArrayList<String> list = new ArrayList<>();
        try (FileWriter f = new FileWriter("OutFile.txt");
             PrintWriter out = new PrintWriter(f)) {
            for (int i = 0; i < 200; i++) {
                out.println("Value at: " + i + " = " + list.get(i));
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
