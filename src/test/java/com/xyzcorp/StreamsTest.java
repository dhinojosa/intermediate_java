package com.xyzcorp;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.assertEquals;

public class StreamsTest {

    @Test
    public void testBasicStream() {
        List<Integer> integers = Arrays.asList(1, 4, 5);
        List<Integer> result = integers.stream()
                                       .map(x -> x + 1).collect(Collectors.toList());
        assertEquals(result, Arrays.asList(2, 5, 6));
    }

    @Test
    public void testBasicWithOurOwnCollection() {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        List<Integer> result = numbers.stream().parallel().map(x -> {
            System.out.format("%s: map: %s\n", Thread.currentThread().getName(), x);
            return x + 1;
        }).collect(
                ArrayList::new,
                (integers, integer) -> {
                    System.out.format("%s: adding integer: %s\n", Thread.currentThread().getName(), integer);
                    integers.add(integer);
                }, (left, right) -> {
                    synchronized (numbers) {
                        System.out.format("%s: left = %s\n", Thread.currentThread().getName(), left);
                        System.out.format("%s: right = %s\n", Thread.currentThread().getName(), right);
                        left.addAll(right);
                        System.out.format("%s: combined = %s\n", Thread.currentThread().getName(), left);
                    }
                });
        assertEquals(result, Arrays.asList(2, 5, 6));
    }

    @Test
    public void testSum() {
        List<Integer> numbers = Arrays.asList(0, 1, 2, 3, 4, 5, 6, 7, 8, 9);
        Integer sum = numbers.stream().map(x -> x + 1).mapToInt(value -> value).sum();
        Integer sum2 = numbers.stream().map(x -> x + 1).collect(Collectors.summingInt(x -> x));
    }

    @Test
    public void testQuestionOnTypes() {
        String collect = Arrays.asList(1, 2, 3, 4)
                               .stream()
                               .map(x -> x + 1)
                               .map(Object::toString)
                               .collect(Collectors.joining(", "));
        assertEquals("2, 3, 4, 5", collect);
    }

    @Test
    public void testConvertToStream() {
        IntStream range = IntStream.range(5, 10);
        IntStream intStream = range.filter(x -> x % 2 == 0);
        Stream<Integer> boxed = intStream.boxed();
        Set<Integer> set = boxed.collect(Collectors.toSet());
        System.out.println(set);
    }

    @Test
    public void testIntStreamSummaryStatistics() {
        Stream<Integer> numbers = Stream.of(100, 33, 22, 400, 30);
        IntStream intStream = numbers.mapToInt(x -> x);
        System.out.println(intStream.summaryStatistics());
    }

    @Test
    public void testStreamWithPeek() {
        List<Integer> result = Stream.of(1, 2, 3, 4, 5)
                                     .map(x -> x + 1)
                                     .peek(System.out::println)
                                     .filter(x -> x % 2 == 0)
                                     .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testLimit() {
        Stream<Integer> integerStream = Stream.iterate(0, x -> x + 1); //Goes on forever!
        List<Integer> result = integerStream.map(x -> x + 4)
                                            .parallel()
                                            .peek(x -> System.out.format("%s: element %s\n", Thread.currentThread().getName(), x))
                                            .limit(10)
                                            .collect(Collectors.toList());
        System.out.println(result);
    }

    @Test
    public void testFlatMap() {
        Stream<Stream<Integer>> streamStream =
                Stream.of(1, 2, 3, 4).map(x -> Stream.of(-x, x, x + 2));
        List<Integer> list = streamStream.collect(
                Collectors.toCollection(ArrayList::new));
        System.out.println(list);
    }
}
