package com.xyzcorp.demos.designpatterns.builder;


import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;

public class BuilderTest {
    @Test
    public void testPetals() {
        Flower flower =
                Flower.builder().petals(7).build();

        assertThat(flower.getPetals()).isEqualTo(7);
    }

    @Test
    public void testLatinName() {
        Flower flower =
                Flower.builder().latinName("Narcissus").build();

        assertThat(flower.getLatinName()).isEqualTo("Narcissus");
    }

    @Test
    public void testLatinNameAndPetals() {
        Flower flower =
                Flower.builder()
                      .latinName("Narcissus")
                      .petals(4)
                      .build();

        assertThat(flower.getLatinName()).isEqualTo("Narcissus");
        assertThat(flower.getPetals()).isEqualTo(7);
    }
}
