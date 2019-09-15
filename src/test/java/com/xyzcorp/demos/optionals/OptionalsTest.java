package com.xyzcorp.demos.optionals;

import org.junit.Test;

import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class OptionalsTest {

    @Test
    public void testOptionalEmpty() {
        Optional<Integer> empty = Optional.empty();
        assertThat(empty).isEmpty();
    }

    @Test
    public void testOptionalNonEmpty() {
        Optional<String> value = Optional.of("Hello");
    }

    @Test
    public void testOptionalFromSomethingPossiblyNull() {
        String possibleNull = getSomethingRandomlyNull();
        Optional<String> optional = Optional.ofNullable(possibleNull);
        if (possibleNull == null) assertThat(optional.isPresent()).isFalse();
        else assertThat(optional.isPresent()).isTrue();
    }

    private String getSomethingRandomlyNull() {
        var random = new java.util.Random();
        if (random.nextBoolean()) {
            return "Foo";
        } else {
            return null;
        }
    }

    @Test
    public void testGettingTheValueTheBadWay() {
        Optional<Long> optionalLong = Optional.empty();
        assertThatThrownBy(optionalLong::get).isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testGettingTheValueTheGoodWayUsingGetOrElse() {
        Optional<Long> optionalLong = Optional.of(40L);
        Long result = optionalLong.orElse(-1L);
        assertThat(result).isEqualTo(-1);
    }


    @Test
    public void testGettingTheValueTheGoodWayUsingOrElseGetSupplier() {
        Optional<Long> optionalLong = Optional.of(40L);
        Long result = optionalLong.orElseGet(this::getDefaultAverage);
        assertThat(result).isEqualTo(30L);
    }

    private Long getDefaultAverage() {
        return 30L;
    }
}
