package com.xyzcorp.exercises.optionals;

import com.xyzcorp.demos.optionals.Pair;
import com.xyzcorp.demos.optionals.Triple;
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
    void testGettingFromNorwayTheCapitalAndPopulationWithString() {
        String country = "Norway";
        Optional<String> capitalOptional =
            Optional.ofNullable(europeanCountriesCapitals.get(country));
        Optional<String> stringOptional =
            capitalOptional.flatMap(capital ->
                Optional.ofNullable(europeanCapitalPopulation.get(capital))
                        .map(population -> String.format("%s, with a " +
                            "population of %d", capital, population)));
        assertThat(stringOptional).contains("Oslo, with a population of " +
            "634293");
    }

    @Test
    void testGettingFromNorwayTheCapitalAndPopulationWithPair() {
        String country = "Norway";
        Optional<String> capitalOptional =
            Optional.ofNullable(europeanCountriesCapitals.get(country));
        Optional<Pair<String, Integer>> optionalPair =
            capitalOptional
                .flatMap(capital ->
                    Optional.ofNullable(
                                europeanCapitalPopulation.get(capital))
                            .map(population ->
                                new Pair<>(capital, population)));
        Optional<String> optionalString =
            optionalPair.map(p -> String.format("%s, with a " +
                "population of %d", p.getA(), p.getB()));
        assertThat(optionalString).contains("Oslo, with a population of " +
            "634293");
    }

    @Test
    void testGettingFromGreeceTheCapitalAndPopulation() {
        String country = "Greece";
        Optional<String> capitalOptional = Optional
            .ofNullable(
                europeanCountriesCapitals.get(country));
        Optional<Pair<String, Integer>> optionalPair =
            capitalOptional
                .flatMap(capital ->
                    Optional.ofNullable(
                                europeanCapitalPopulation.get(capital))
                            .map(population ->
                                new Pair<>(capital, population)));
        Optional<String> s = optionalPair.map(p -> String.format("%s, with a " +
            "population of %d", p.getA(), p.getB()));
        assertThat(s).isEmpty();
    }

    @Test
    void testGettingFromNorwayTheCountryAndCapitalAndPopulation() {
        String country = "Norway";
        Optional<Triple<String, String, Integer>> tripleOptional =
            Optional.ofNullable(europeanCountriesCapitals.get(country))
                    .flatMap(capital ->
                        Optional.ofNullable(europeanCapitalPopulation.get(capital))
                                .map(population -> new Triple<>(country,
                                    capital, population)));
        Optional<String> result = tripleOptional.map(t -> String.format("%s, " +
            "with " +
            "the capital %s, has a " +
            "population of %d", t.getA(), t.getB(), t.getC()));

        assertThat(result).contains("Norway, with the capital Oslo, has a " +
            "population of 634293");
    }

    @Test
    void testGettingFromNorwayTheCountryAndCapitalAndPopulationWithThreeOptionals() {

        Optional<String> countryOptional = Optional.of("Norway");

        Optional<Triple<String, String, Integer>> tripleOptional =
            countryOptional
                .flatMap(country ->
                Optional.ofNullable(europeanCountriesCapitals.get(country))
                        .flatMap(capital ->
                            Optional.ofNullable(europeanCapitalPopulation.get(capital))
                                    .map(population -> new Triple<>(country,
                                        capital, population))));

        Optional<String> result = tripleOptional.map(t -> String.format("%s, " +
            "with " +
            "the capital %s, has a " +
            "population of %d", t.getA(), t.getB(), t.getC()));

        assertThat(result).contains("Norway, with the capital Oslo, has a " +
            "population of 634293");
    }
}
