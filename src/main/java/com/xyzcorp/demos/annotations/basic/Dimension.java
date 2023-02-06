package com.xyzcorp.demos.annotations.basic;

import java.util.Objects;
import java.util.StringJoiner;

@AuthoredBy(firstName = "Wilma", lastName = "Flintstone")
public class Dimension {
    private final int length;
    private final int height;

    public Dimension(int length, int height) {
        this.length = length;
        this.height = height;
    }

    @AuthoredBy(firstName = "Englebert", lastName = "Humperdinck")
    public int getLength() {
        return length;
    }

    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dimension dimension = (Dimension) o;
        return length == dimension.length && height == dimension.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(length, height);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Dimension.class.getSimpleName() + "[",
            "]")
            .add("length=" + length)
            .add("height=" + height)
            .toString();
    }
}
