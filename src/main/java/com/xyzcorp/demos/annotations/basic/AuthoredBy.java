package com.xyzcorp.demos.annotations.basic;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface AuthoredBy {
    public String firstName();
    public String lastName();
}
