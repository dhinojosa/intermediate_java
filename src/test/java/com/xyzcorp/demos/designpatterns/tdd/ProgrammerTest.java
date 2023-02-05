package com.xyzcorp.demos.designpatterns.tdd;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.function.Supplier;


public class ProgrammerTest {

    @Test
    void testBjarneFullName() {
        //first: FirstName, second: LastName, third: BirthDate, fourth: Supplier of current Date
        Programmer programmer = new Programmer("Bjarne",
                "Strousoup", LocalDate.of(1950, 12, 30),
                () -> LocalDate.of(2018, 8, 20));

        assertThat(programmer.fullName()).isEqualTo("Bjarne Strousoup");
    }

    @Test
    void testGoslingFullName() {
        Programmer programmer = new Programmer("James",
                "Gosling", LocalDate.of(1955, 5, 19),
                () -> LocalDate.of(2018, 8, 20));
        assertThat(programmer.fullName()).isEqualTo("James Gosling");
    }

    @Test
    void testBjarneAge() {
        Programmer programmer = new Programmer(
                "Bjarne", "Strousoup", LocalDate.of(1950, 12, 30),
                () -> LocalDate.of(2018, 8, 20));
        assertThat(programmer.getAge()).isEqualTo(67);
    }

    //Unit test - isolated, runnable
    @Test
    void testGoslingAge() {
        Programmer programmer = new Programmer(
                "James", "Gosling", LocalDate.of(1955, 5, 19),
                () -> LocalDate.of(2018, 8, 20));
        assertThat(programmer.getAge()).isEqualTo(63);
    }

    //Integration Test - non-isolation

    @Test
    void testGoslingWithActualDate() {
        Programmer programmer = new Programmer(
                "James", "Gosling", LocalDate.of(1955, 5, 19),
                LocalDate::now);
        assertThat(programmer.getAge()).isEqualTo(63);
    }

    @Test
    public void testCreateProgrammerUsingAFactory() {
        Programmer programmer = ProgrammerFactory
                .create("Guido", "Van Rossum", LocalDate.of(1956, 1, 31));
        assertThat(programmer.getAge()).isEqualTo(62);
    }
}
