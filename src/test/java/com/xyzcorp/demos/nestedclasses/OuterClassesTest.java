package com.xyzcorp.demos.nestedclasses;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class OuterClassesTest {


    @Test
    void testUsingMap() {
        Map<String, String> countiesAndCapitals = new HashMap<>();
        countiesAndCapitals.put("Brazil", "Brasilia");
        countiesAndCapitals.put("United States", "Washington, D.C.");
        countiesAndCapitals.put("Croatia", "Zagreb");

        assertThat(countiesAndCapitals.get("Brazil")).isEqualTo("Brasilia");
    }

    @Test
    void testUsingMapUsingOf() {
        Map<String, String> countiesAndCapitals = Map.of(
            "Brazil", "Brasilia",
            "United States", "Washington, D.C.",
            "Croatia", "Zagreb");
        assertThat(countiesAndCapitals.get("Brazil")).isEqualTo("Brasilia");
    }

    @Test
    void testUsingMapUsingOfEntries() {
        Map.Entry<String, String> brazilIsAwesome = Map.entry("Brazil", "Brasilia");
        Map<String, String> countiesAndCapitals = Map.ofEntries(
            brazilIsAwesome,
            Map.entry("United States", "Washington, D.C."),
            Map.entry("Croatia", "Zagreb"));
        assertThat(countiesAndCapitals.get("Brazil")).isEqualTo("Brasilia");
    }

    @Test
    public void testInnerClasses() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        assertThat(innerClass.sum()).isEqualTo(6);
    }

    @Test
    public void testInnerClassesWithState() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.new InnerClass();
        assertThat(innerClass.sumCond(30)).isEqualTo(56);
        OuterClass.InnerClass innerClass2 = outerClass.new InnerClass();

        assertThat(innerClass2.sumCond(20)).as("A").isEqualTo(56); // A (*)
//        assertThat(innerClass2.sumCond(20)).as("B").isEqualTo(36); // B (0)
    }

    @Test
    public void testUsingClassesWithInnerClasses() {
        OuterClass outerClass = new OuterClass();
        assertThat(outerClass.foo()).isEqualTo(5);
    }

    @Test
    void testAccessingTheInnerClass() {
        OuterClass outerClass = new OuterClass();
        OuterClass.InnerClass innerClass = outerClass.bar();
        assertThat(innerClass.sum()).isEqualTo(6);
    }

    @Test
    void testPurelyAccessingAStaticVariable() {
        int result = OuterClass.baz();
        assertThat(result).isEqualTo(29);
    }

    @Test
    public void testUsingClassesWithStaticInnerClasses() {
        OuterClass.StaticInnerClass staticInnerClass =
            new OuterClass.StaticInnerClass();
        assertThat(staticInnerClass.bar()).isEqualTo(48);
    }
}
