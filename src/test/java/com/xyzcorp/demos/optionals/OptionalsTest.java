package com.xyzcorp.demos.optionals;

import com.xyzcorp.demos.collections.CollectionsTest;
import com.xyzcorp.demos.generics.people.Person;
import org.junit.jupiter.api.Test;

import java.util.*;
import java.util.function.Predicate;

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
        assertThat(value).contains("Hello");
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
        assertThatThrownBy(optionalLong::get)
            .isInstanceOf(NoSuchElementException.class);
    }

    @Test
    public void testGettingTheValueTheGoodWayUsingGetOrElse() {
        Optional<Long> optionalLong = Optional.of(40L);
        Long result = optionalLong.orElse(-1L);
        assertThat(result).isEqualTo(40L);
    }

    @Test
    public void testGettingTheValueTheGoodWayUsingOrElseGetSupplier() {
        Optional<Long> optionalLong = Optional.of(40L);
        Long result = optionalLong.orElseGet(() -> getDefaultAverage());
        assertThat(result).isEqualTo(40L);
    }

    @Test
    void testIsPresentOrElse() {
        Optional<Long> optionalLong = Optional.of(40L);
        optionalLong
            .ifPresentOrElse(
                x -> assertThat(x).isEqualTo(40L),
                () -> System.out.println("Not good"));
    }

    @Test
    public void testPriorityQueue() throws Exception {
        Queue<Person> queue = new PriorityQueue<>(
            Comparator.comparing(Person::getLastName));
        queue.offer(new Person("Franz", "Kafka"));
        queue.offer(new Person("Jane", "Austen"));
        queue.offer(new Person("Leo", "Tolstoy"));
        queue.offer(new Person("Lewis", "Carroll"));
        String result = Optional.ofNullable(queue.peek())
                                .map(Person::getLastName)
                                .orElse("");
        assertThat(result).isEqualTo("Austen");
    }

    @Test
    void testFilter() {
        Optional<Integer> a = Optional.of(30);
        Optional<Integer> result =
            a.filter(integer -> integer > 50);
        assertThat(result).isEmpty();
    }

    @Test
    void testMap() {
        Optional<Integer> a = Optional.of(30);
        Optional<String> s = a.map(i -> i + "!");
        assertThat(s).contains("30!");
    }

    @Test
    void testFlatMapWithTwoOptionals() {
        Optional<Integer> a = Optional.of(30);
        Optional<Integer> b = Optional.of(60);
        Optional<Integer> result = a.flatMap(x -> b.map(y -> x + y));
        assertThat(result).contains(90);
        assertThat(a).contains(30);
        assertThat(b).contains(60);
    }

    @Test
    void testFlatMapWithThreeOptionals() {
        Optional<Integer> a = Optional.of(30);
        Optional<Integer> b = Optional.of(60);
        Optional<Integer> c = Optional.of(120);
        Optional<Integer> result =
            a.flatMap(x ->
                b.flatMap(y ->
                    c.map(z -> x + y + z)));
        assertThat(result).contains(210);
        assertThat(a).contains(30);
        assertThat(b).contains(60);
        assertThat(c).contains(120);
    }

    @Test
    void testFlatMapWithFourOptionals() {
        Optional<Integer> a = Optional.of( 30);
        Optional<Integer> b = Optional.of( 60);
        Optional<Integer> c = Optional.of(120);
        Optional<Integer> d = Optional.of(420);
        Optional<Integer> result =
            a.flatMap(x ->
                b.flatMap(y ->
                    c.flatMap(z ->
                        d.map(v -> x + y + z + v))));
        assertThat(result).contains(630);
        assertThat(a).contains(30);
        assertThat(b).contains(60);
        assertThat(c).contains(120);
        assertThat(d).contains(420);
    }

    @Test
    void testFlatMapWithFourOptionalsWithOneEmpty() {
        Optional<Integer> a = Optional.of( 30);
        Optional<Integer> b = Optional.of( 60);
        Optional<Integer> c = Optional.empty();
        Optional<Integer> d = Optional.of(420);
        Optional<Integer> result =
            a.flatMap(x ->
                b.flatMap(y ->
                    c.flatMap(z ->
                        d.map(v -> x + y + z + v))));
        assertThat(result).isEmpty();
        assertThat(a).contains(30);
        assertThat(b).contains(60);
        assertThat(c).isEmpty();
        assertThat(d).contains(420);
    }


    private Long getDefaultAverage() {
        return 30L;
    }

    @Test
    void testUsingAMap() {
        //TBD
    }

    @Test
    void testUsingFunctional() {

    }
}
