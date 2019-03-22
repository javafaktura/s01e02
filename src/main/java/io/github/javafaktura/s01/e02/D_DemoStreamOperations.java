package io.github.javafaktura.s01.e02;

import io.github.javafaktura.s01.e02.helper.GroupedCountReducer;
import io.github.javafaktura.s01.e02.helper.ResidentialAreaHelper;
import io.github.javafaktura.s01.e02.model.*;
import org.apache.commons.lang3.time.StopWatch;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static io.github.javafaktura.s01.e02.util.Pretty.*;
import static java.util.stream.Collectors.counting;
import static java.util.stream.Collectors.groupingBy;

/**
 * @author Adam Król 2019-03-20
 */
public class D_DemoStreamOperations {

    public static void main(String[] args) {
        List<Person> people = ResidentialAreaHelper.randomPeople(10);
        ____________("Collection to work with:");
        verboseCollectionPrint(people);


        ____________("Printing stream");
        printStream(people.stream());


        //Map
        ____________("Map objects");
        //lets have only name of every person
        List<String> names = new ArrayList<>();
        for (Person p : people) {
            names.add(p.getFullName());
        }
        verboseCollectionPrint(names);


        ____________("You'd better map");
        //Those are equal
        printStream(
                people.stream().map(p -> p.getFullName())
        );


        //Filter Stream
        //Person who have more than 10000
        ____________("Filtering");
        printStream(
                people.stream()
                        .filter(p -> p.getSalary() > 10_000)
                        .map(person -> person.getFullName() + " " + person.getSalary())
        );
        //Remember to filter it first


        //Streams are lazy
        ____________("Lazy wait");
        StopWatch stopWatch = StopWatch.createStarted();
        printStream(
        people.stream()
                .filter(p -> p.getSalary() > 10_000)
                .map(D_DemoStreamOperations::identitySleep)
        );
        stopWatch.stop();
        System.out.println(stopWatch.getTime());


        //Streams reuse
        //Why two examples above didn't use one stream?
        ____________("Stream reuse");
        Stream<Person> personStreamToReUse = people.stream();
        printStream(personStreamToReUse.filter(p -> p.getSex() == Sex.MALE));
//        printStream(personStreamToReUse.filter(Person::isMarried));
        //chaining is valid
        ____________("Man and married");
        printStream(
                ResidentialAreaHelper.randomPeopleStream(10)
                        .filter(p -> p.getSex() == Sex.MALE)
                        .filter(Person::isMarried)
        );


        ____________("Collect stream");
        //Stream collect
        List<Person> collected = people.stream()
                .collect(ArrayList::new, List::add, List::addAll);
                //How to create new object, How to add to existing object, How to merge
        verboseCollectionPrint(collected);


        ____________("Collect stream with Collectors");
        //Fortunatelly Java has built in some nice collectors
        verboseCollectionPrint(
                people.stream().collect(Collectors.toList()) //The most common used one
                //Collectors.toSet()
        );


        ____________("Collect with joining");
        //Very commonly used
        String namesSeparated = people.stream()
                .map(Person::getFullName)
                .collect(Collectors.joining(", "));
        System.out.println(namesSeparated);


        ____________("Advanced collecting");
        Map<Sex, Integer> countBySex = people.stream()
                .map(Person::getSex)
                .collect(HashMap::new,
                        (map, sex) -> map.put(sex, map.getOrDefault(sex, 0) + 1),
                        (map, map2) -> map.forEach(
                                (key, value) -> map2.put(key, map2.getOrDefault(key, 0) + value)));
        verboseMapPrint(countBySex);



        verboseMapPrint(
                people.stream().collect(groupingBy(Person::getSex, counting()))
        );


        ____________("Sorting stream");
        printStream(
                people.stream()
                        .sorted((p1, p2) -> p1.getSalary() - p2.getSalary())
        );


        ____________("Reducing stream");
        //When we want to convert stream into one element
        //We are forced to use binaryOperator and always return new value
        Optional<Person> reducedPerson = people.stream()
                .reduce((person, person2) -> {
                    person.setFullName(person.getFullName() + ", " + person2.getFullName());
                    return person;
                });
        System.out.println("Reduced person name: " + reducedPerson.get());

        Person onePersonReduced = people.stream()
                .skip(9)
                .reduce(Person.random(), (person, person2) -> {
                    person.setFullName(person.getFullName() + ", " + person2.getFullName());
                    return person;
                });
        System.out.println("Reduced one person: " + onePersonReduced);

        Integer salarySum = people.stream().parallel()
                .reduce(0,
                (integer, person) -> integer + person.getSalary(),
                (integer, integer2) -> integer + integer2);
        System.out.println("Salary sum:" + salarySum);

        GroupedCountReducer<Sex> objectReduced = people.stream()
                .reduce(new GroupedCountReducer<>(),
                    (sexGroupedCountReducer, person) -> sexGroupedCountReducer.add(person.getSex()),
                    GroupedCountReducer::add);
        verboseMapPrint(objectReduced.getCountingMap());


        ____________("Flat Map");
        //Lets count all residents in for loop
        List<Block> blocks = ResidentialAreaHelper.randomArea(4).collect(Collectors.toList());

        int sum = 0;
        for (Block b: blocks) {
            for (Floor f: b.getFloors()) {
                for (Flat flat: f.getFlats()) {
                    sum += flat.getResidents().size();
                }
            }
        }
        System.out.println("Count of all residents: " + sum);

        //Lets map it
        Stream<List<Floor>> listStream = blocks.stream()
                .map(Block::getFloors); //Not an option

        Stream<Floor> floorStream = blocks.stream()
                .flatMap(b -> b.getFloors().stream()); //Nice

        long count = blocks.stream()
                .flatMap(b -> b.getFloors().stream())
                .flatMap(f -> f.getFlats().stream())
                .mapToLong(f -> f.getResidents().size())
                .sum();
        System.out.println("Counted with streams: " + count);

    }

    private static Person identitySleep(Person p) {
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return p;
    }
}
