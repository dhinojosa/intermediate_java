package com.xyzcorp.exercises.optionals;

import org.junit.jupiter.api.Test;

import java.util.AbstractMap;
import java.util.Map;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class OptionalExercises {

    private Map<String, String> europeanCountriesCapitals = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("France", "Paris"),
        new AbstractMap.SimpleEntry<>("Britain", "London"),
        new AbstractMap.SimpleEntry<>("Bulgaria", "Sofia"),
        new AbstractMap.SimpleEntry<>("Spain", "Madrid"),
        new AbstractMap.SimpleEntry<>("Greece", "Athens"),
        new AbstractMap.SimpleEntry<>("Poland", "Warsaw"),
        new AbstractMap.SimpleEntry<>("Germany", "Berlin"),
        new AbstractMap.SimpleEntry<>("Norway", "Oslo"),
        new AbstractMap.SimpleEntry<>("Finland", "Helsinki"),
        new AbstractMap.SimpleEntry<>("Sweden", "Stockholm"),
        new AbstractMap.SimpleEntry<>("Portugal", "Lisbon"));

    private Map<String, Integer> europeanCapitalPopulation = Map.ofEntries(
        new AbstractMap.SimpleEntry<>("Paris", 2_141_000),
        new AbstractMap.SimpleEntry<>("London", 8_136_000),
        new AbstractMap.SimpleEntry<>("Sofia", 1_236_000),
        new AbstractMap.SimpleEntry<>("Spain", 3_174_000),
        new AbstractMap.SimpleEntry<>("Warsaw", 1_765_000),
        new AbstractMap.SimpleEntry<>("Berlin", 3_575_000),
        new AbstractMap.SimpleEntry<>("Oslo", 634_293),
        new AbstractMap.SimpleEntry<>("Helsinki", 631_695),
        new AbstractMap.SimpleEntry<>("Stockholm", 965_232),
        new AbstractMap.SimpleEntry<>("Lisbon", 504_718));

    @Test
    void testGettingGreeceCapital() {
        Optional<String> optional =
            Optional.ofNullable(europeanCountriesCapitals.get("Greece"));
        assertThat(optional).contains("Athens");
    }

    @Test
    void testGettingHungaryCapital() {
        Optional<String> optional =
            Optional.ofNullable(europeanCountriesCapitals.get("Hungary"));
        assertThat(optional).isEmpty();
    }

    @Test
    void testGettingFromNorwayTheCapitalAndPopulation() {
        String country = "Norway";
        Optional<String> capitalOptional =
            Optional.ofNullable(europeanCountriesCapitals.get(country));
        Optional<String> stringOptional =
            capitalOptional.flatMap(capital ->
                  Optional.ofNullable(europeanCapitalPopulation.get(capital))
                          .map(population -> String.format("%s %s", capital, population)));
        assertThat(stringOptional).contains("Oslo 634293");
    }

    @Test
    void testGettingFromGreeceTheCapitalAndPopulation() {

    }

    @Test
    void testGettingFromNorwayTheCountryAndCapital() {

    }
}
