package io.github.javafaktura.s01.e02.helper;

import io.github.javafaktura.s01.e02.model.Block;
import io.github.javafaktura.s01.e02.model.Person;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-03-20
 */
public class ResidentialAreaHelper {

    public static List<Person> randomPeople(int count) {
        return randomPeopleStream(count).collect(toList());
    }

    public static Stream<Person> randomPeopleStream(int count) {
        return IntStream.range(0, count).mapToObj(v -> Person.random());
    }

    public static Stream<Block> randomArea(int blocks) {
        return IntStream.range(0, blocks).mapToObj(i -> Block.random(i + 1, new Random().nextInt(5)));
    }
}
