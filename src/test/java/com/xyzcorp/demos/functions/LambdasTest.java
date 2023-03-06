package com.xyzcorp.demos.functions;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.StringJoiner;


public class LambdasTest {
    @Test
    public void testMyFilter() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8, 10, 11, 14, 15);
        List<Integer> result = Functions.myFilter(numbers,
            item -> item % 2 == 0);
        assertThat(Arrays.asList(4, 8, 10, 14)).isEqualTo(result);
    }


    class Euro {
        private final int value;

        public Euro(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        @Override
        public String toString() {
            return new StringJoiner(", ", Euro.class.getSimpleName() + "[", "]")
                .add("value=" + value)
                .toString();
        }
    }

    @Test
    public void testMyMap() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        List<Integer> mapped = Functions.myMap(numbers, t -> t + 2);
        assertThat(Arrays.asList(6, 7, 9, 10)).isEqualTo(mapped);
    }

    @Test
    public void testMyMapWithDifferentReturnValue() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        List<String> strings = Functions.myMap(numbers, Integer::toHexString);
        assertThat(strings).isEqualTo(List.of("4", "5", "7", "8"));
    }

    public void testMyMapWithValueObject() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        List<Euro> result = Functions.myMap(numbers, Euro::new);//whoa
        assertThat(result).isEqualTo(List.of(new Euro(4), new Euro(5),
            new Euro(7), new Euro(8)));
    }

    @Test
    void testMyMapWhereReturningAPluralForEveryElement() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        List<List<Integer>> result = Functions.myMap(numbers,
            integer -> List.of(-integer, integer + 1, integer + 2));
        assertThat(result).isEqualTo(List.of(List.of(-4, 5, 6), List.of(-5, 6
                , 7),
            List.of(-7, 8, 9), List.of(-8, 9, 10)));
    }

    @Test
    void testMyFlatMap() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        List<Integer> result = Functions.myFlatMap(numbers,
            integer -> List.of(-integer, integer + 1, integer + 2));
        assertThat(result).isEqualTo(List.of(-4, 5, 6, -5, 6, 7, -7, 8, 9, -8
            , 9, 10));
    }

    @Test
    void testMyFlatMapAsTheCrusher() {
        List<List<Integer>> lists =
            Arrays.asList(
                Arrays.asList(1, 2, 3),
                Arrays.asList(5, 7, 8),
                Arrays.asList(10, 11, 12));
        List<Integer> result = Functions.myFlatMap(lists, x -> x);
        assertThat(result).isEqualTo(List.of(1, 2, 3, 5, 7, 8, 10, 11, 12));
    }

    @Test
    void testMyFlatMapAsWordCounter() {
        List<String> lyrics = List.of("I see trees of green",
            "Red roses too",
            "I see them bloom", "For me and you", "And I think to myself",
            "What a wonderful world");

        List<String> words = Functions.myFlatMap(lyrics, s -> Arrays.asList(s.split(" ")));
        List<String> lowerCaseWords = Functions.myMap(words, String::toLowerCase);
        System.out.println(lowerCaseWords);
    }

    @Test
    public void testMyForEach() {
        List<Integer> numbers = Arrays.asList(4, 5, 7, 8);
        Functions.myForEach(numbers, System.out::println);
    }

    @Test
    public void testMethodReferenceAStaticMethod() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        System.out.println(Functions.myMap(numbers, Math::abs));
    }

    @Test
    public void testMethodReferenceAContainingType() {
        List<String> words = Arrays.asList("One", "Two", "Three", "Four");
        List<Integer> result = Functions.myMap(words, String::length);
        System.out.println(result);
        Functions.myFilter(Functions.myMap(words, String::length), x -> x < 3);
    }

    @Test
    public void testMethodReferenceAContainingTypeTrickQuestion() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        System.out.println(Functions.myMap(numbers, Object::toString));
    }


    @Test
    public void testTwoOrMoreItemsInALambda() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        System.out.println(Functions.myMap(numbers, x -> {
            int y = 100;
            int z = 10;
            return y + z + x;
        }));
    }

    @Test
    public void testMethodReferenceAnInstance() {
        List<Integer> numbers = Arrays.asList(2, 4, 5, 1, 9, 15, 19,
            21, 33, 78, 93, 10);
        TaxRate taxRate2016 = new TaxRate(2016, .085);
        TaxRate taxRate2017 = new TaxRate(2017, .06);
        System.out.println(Functions.myMap(numbers, taxRate2016::apply));
    }

    @Test
    public void testMyGenerate() {
        List<LocalDateTime> localDateTimes =
            Functions.myGenerate(() -> {
                sleepForOneSecond();
                return LocalDateTime.now();
            }, 10);
        System.out.println(localDateTimes);
    }

    private static void sleepForOneSecond() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            //Ignore the exception
        }
    }

    @Test
    public void testLambdasWithRunnable() {
        printThreadMessage("Hello from main thread");
        Thread t = new Thread(() -> printThreadMessage("Hello from another thread"));
        t.start();
    }

    private static void printThreadMessage(String message) {
        String threadName =
            Thread.currentThread().getName();
        System.out.format("%s: %s%n",
            threadName,
            message);
    }

    public MyPredicate<String> stringHasSizeOf(final int length) {
        return item -> item.length() == length;
    }

    @Test
    public void testClosuresAvoidRepeats() {
        List<String> names = Arrays.asList("Foo", "Ramen", "Naan", "Ravioli");
        System.out.println(Functions.myFilter(names, stringHasSizeOf(4)));
        System.out.println(Functions.myFilter(names, stringHasSizeOf(2)));
    }

    @Test
    public void testOptionalForTruth() {
        Optional<String> middleName1 = Optional.of("Mahesh");
        Optional<String> middleName2 = Optional.empty();

        String result1 = middleName1.orElseGet(() -> "No name found");
        String result2 = middleName2.orElseGet(() -> "No name found");

        assertThat("Mahesh").isEqualTo(result1);
        assertThat("No name found").isEqualTo(result2);
    }
}



