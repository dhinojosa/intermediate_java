package com.xyzcorp.demos.designpatterns.builder;

import com.google.common.collect.ImmutableBiMap;
import com.google.errorprone.annotations.Immutable;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.HashMap;
import java.util.Map;

public class GuavaBuilderTest {

    @Test
    void testImmutableBiMap() {
        HashMap<String, String> anotherMap = new HashMap<>();
        anotherMap.put("Coffee", "Kapi");
        anotherMap.put("Rainbow", "Indradhanush");

        ImmutableBiMap<String, String> englishToHindi =
            ImmutableBiMap.<String, String>builder()
                          .put("Cloud", "Badal")
                          .put("Light", "Prakash")
                          .putAll(anotherMap)
                          .build();

        assertThat(englishToHindi.get("Cloud")).isEqualTo("Badal");

        ImmutableBiMap<String, String> hindiToEnglish =
            englishToHindi.inverse();

        assertThat(hindiToEnglish.get("Prakash")).isEqualTo("Light");
        assertThat(englishToHindi.get("Coffee")).isEqualTo("Kapi");
    }
}
