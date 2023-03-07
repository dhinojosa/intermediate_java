package com.xyzcorp.demos.optionals;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class AccountTest {

    @Test
    void testAccountWithMiddleName() {
        Account account = new Account("Wendel", "Santos", "dos");
        Optional<String> middleName = account.getMiddleName();
        var result = middleName.orElse("<None Available>");
        assertThat(result).isEqualTo("dos");
    }

    @Test
    void testAccountWithNoMiddleName() {
        Account account = new Account("Daniel", "Hinojosa");
        Optional<String> middleName = account.getMiddleName();
        var result = middleName.orElse("<None Available>");
        assertThat(result).isEqualTo("<None Available>");
    }
}
