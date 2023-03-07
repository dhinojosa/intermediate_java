package com.xyzcorp.demos.optionals;

import com.google.common.base.Strings;

import java.util.Objects;
import java.util.Optional;
import java.util.StringJoiner;

public class Account {
    private final String firstName;
    private final String lastName;

    //Don't use Optional in a member variable,
    //not recommended
    //private Optional<String> middleName;
    private final String middleName;

    public Account(String firstName, String lastName) {
        this(firstName, lastName, null);
    }

    public Account(String firstName, String lastName, String middleName) {
        Objects.requireNonNull(firstName, "First Name cannot be null");
        Objects.requireNonNull(lastName, "First Name cannot be null");
        if(firstName.isBlank()) throw new IllegalArgumentException("First Name cannot be empty");
        if(lastName.isBlank()) throw new IllegalArgumentException("Last Name cannot be empty");
        if(middleName != null && middleName.isBlank())
            throw new IllegalArgumentException("Middle Name cannot be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.middleName = middleName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Optional<String> getMiddleName() {
        return Optional.ofNullable(middleName);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return firstName.equals(account.firstName) && lastName.equals(account.lastName) && Objects.equals(middleName, account.middleName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstName, lastName, middleName);
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Account.class.getSimpleName() + "[", "]")
            .add("firstName='" + firstName + "'")
            .add("lastName='" + lastName + "'")
            .add("middleName='" + middleName + "'")
            .toString();
    }
}
