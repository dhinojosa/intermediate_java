package com.xyzcorp.exercises.functions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FunctionExercisesTest {
    /**
     * This is a standard JUnit 5 Test with
     * a standard JUnit5 assertion, you can
     * use this as a guide until we can
     * dig deeper into JUnit 5
     */
    @Test
    void testSampleWithStandardJUnit() {
        var result = Math.max(40, 3);
        assertEquals(40, result);
    }

    /**
     * This is a standard JUnit 5 Test with
     * an AssertJ assertion, you can
     * use this as a guide until we can
     * dig deeper into AssertJ
     */
    @Test
    void testSampleWithStandardJUnitAndAssertJAssertion() {
        var result = Math.max(40, 3);
        assertThat(result).isEqualTo(40);
    }

    @Test
    void testMyTimer() {
        TimeResult<Integer> result = MyTimer.measureTime(() -> {
            try {
                Thread.sleep(4000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return 40;
        });

        assertThat(result.getResult()).isEqualTo(40);
        assertThat(result.getTime()).isGreaterThanOrEqualTo(4000);
    }
}
