package com.xyzcorp;

import com.xyzcorp.people.EyeColor;
import com.xyzcorp.people.Person;
import org.junit.Test;

import java.util.*;

public class CollectionsTest {

    @Test
    public void hashSetMutability() throws Exception {
        Person person = new Person();
        person.setFirstName("Angelina");
        person.setLastName("Jolie");
        person.setEyeColor(EyeColor.BROWN);

        HashSet<Person> hashSet = new HashSet<>();
        hashSet.add(person);

        System.out.println(hashSet.contains(person));

        person.setEyeColor(EyeColor.AMBER);
        System.out.println(hashSet.contains(person));
    }

    @Test
    public void testUsingMap() {
        Map<String, String> teams = new HashMap<>();
        teams.put("San Francisco", "49ers");
        teams.put("Las Vegas", "Raiders");
    }
}
