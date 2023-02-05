package com.xyzcorp.demos.nestedclasses;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class OuterClassesTest {

    @Test
    public void testInnerClasses() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        assertThat(innerClass.sum()).isEqualTo(6);
    }

    @Test
    public void testUsingClassesWithInnerClasses() {
        OuterClass outerClass = new OuterClass();
        assertThat(outerClass.foo()).isEqualTo(5);
    }

    @Test
    public void testUsingClassesWithStaticInnerClasses() {
        OuterClass.StaticInnerClass staticInnerClass = new OuterClass
            .StaticInnerClass();
        assertThat(staticInnerClass.bar()).isEqualTo(48);
    }
}
