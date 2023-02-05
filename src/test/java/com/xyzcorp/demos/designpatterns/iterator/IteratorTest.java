package com.xyzcorp.demos.designpatterns.iterator;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


public class IteratorTest {

    @Test
    public void testIteratorFromList() {
        var stringList = List.of("Foo", "Bar", "Baz", "Qux", "Quux");
        Iterator<String> iterator = stringList.iterator();

        String value1 = iterator.next();
        String value2 = iterator.next();

        assertThat(value1).isEqualTo("Foo");
        assertThat(value2).isEqualTo("Bar");
    }

    @Test
    public void testIteratorFromListTrickQuestion() {
        var stringList = List.of("Foo", "Bar", "Baz", "Qux", "Quux");
        String value1 = stringList.iterator().next();
        String value2 = stringList.iterator().next();

        assertThat(value1).isEqualTo("Foo");
        assertThat(value2).isEqualTo("Foo");
    }

    @Test
    public void testIteratorUsingWhileLoop() {
        var iterator = List.of("Foo", "Bar", "Baz", "Qux", "Quux").iterator();
        var result = new ArrayList<String>();
        while(iterator.hasNext()) {
            result.add(iterator.next());
        }
        assertThat(result.toString()).isEqualTo("[Foo, Bar, Baz, Qux, Quux]");
    }

    @SuppressWarnings("UseBulkOperation")
    @Test
    public void testIteratorWithEnhancedForLoop() {
        var list = List.of("Foo", "Bar", "Baz", "Qux", "Quux");
        var result = new ArrayList<String>();
        for (String s : list) {
            result.add(s);
        }
        assertThat(result.toString()).isEqualTo("[Foo, Bar, Baz, Qux, Quux]");
    }

    @Test
    public void testListIteratorLoop() {
        var list = List.of("Foo", "Bar", "Baz", "Qux", "Quux");
        var listIterator = list.listIterator();
        listIterator.next();
        listIterator.next();
        listIterator.next();
        //listIterator.add("Nice"); Unsupported

        listIterator.previous();
        listIterator.previous();
        listIterator.next();
        assertThat(listIterator.next()).isEqualTo("Baz");
    }


    @Test
    public void testListIteratorLoopWithModification() {
        var list = new ArrayList<String>();
        list.add("One");
        list.add("Two");
        list.add("Three");
        list.add("Four");
        var listIterator = list.listIterator();
        listIterator.next(); //One
        listIterator.next(); //Two
        listIterator.add("Nice");
        String afterAdd = listIterator.next(); //Three
        assertThat(afterAdd).isEqualTo("Three");
        listIterator.previous(); //Three
        listIterator.previous(); //Nice
        listIterator.next(); //Two
        assertThat(listIterator.next()).isEqualTo("Three");
        assertThat(list).isEqualTo(List.of("One", "Two", "Nice", "Three", "Four"));
    }

    @Test
    public void testSpliteratorWithForEachRemaining() {
        var list = IntStream.range(1, 1000).boxed().collect(Collectors.toList());
        var split1 = list.spliterator();
        var split2 = split1.trySplit();
        var split3 = split2.trySplit();
        var split4 = split1.trySplit();
        split1.forEachRemaining(x -> System.out.println("S1 " + x));
        split2.forEachRemaining(x -> System.out.println("S2 " + x));
        split3.forEachRemaining(x -> System.out.println("S3 " + x));
        split4.forEachRemaining(x -> System.out.println("S4 " + x));
    }

    @Test
    public void testSpliteratorWithTryAdvance() {
        var list = List.of("Foo", "Bar", "Baz", "Qux", "Quux");
        var split1 = list.spliterator();
        var split2 = split1.trySplit();
        split1.tryAdvance(x -> System.out.println("S1 " + x)); //Baz
        split2.tryAdvance(x -> System.out.println("S2 " + x)); //Foo
    }
}
