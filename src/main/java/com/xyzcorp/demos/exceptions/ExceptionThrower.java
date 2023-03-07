package com.xyzcorp.demos.exceptions;

public class ExceptionThrower {
    public static void throwCheckedException() throws Exception {
        throw new Exception("This is a checked exception");
    }

    public static void throwRuntimeException() {
        throw new RuntimeException("This is a runtime exception");
    }

    public static void throwCustomException() throws CustomException {
        throw new CustomException("Yay! I create my own");
    }
}
