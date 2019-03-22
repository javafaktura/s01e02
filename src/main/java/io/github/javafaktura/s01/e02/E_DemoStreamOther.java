package io.github.javafaktura.s01.e02;

import io.github.javafaktura.s01.e02.helper.ResidentialAreaHelper;
import io.github.javafaktura.s01.e02.model.Person;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import static io.github.javafaktura.s01.e02.util.Pretty.____________;
import static io.github.javafaktura.s01.e02.util.Pretty.verboseCollectionPrint;
import static java.util.stream.Collectors.joining;

/**
 * @author Adam Król 2019-03-21
 */
public class E_DemoStreamOther {

    public static void main(String[] args) {
        List<Person> people = ResidentialAreaHelper.randomPeople(10);
        System.out.println("Collection to work with:");
        verboseCollectionPrint(people);


        ____________("Min and Max");
        //Both are reductions
        //Both returning Optional in case of empty stream
        Optional<Person> minSalary = people.stream().min(Comparator.comparingInt(Person::getSalary));
        System.out.println("Min salary: " + minSalary.get());


        Optional<Person> maxSalary = people.stream().max(Comparator.comparingInt(Person::getSalary));
        System.out.println("Max salary: " + maxSalary.get());


        ____________("Matching");
        boolean everyoneMarried = true;
        for (Person person : people) {
            if (!person.isMarried()) {
                everyoneMarried = false;
                break;
            }
        }
        System.out.println("Simple everyone married: " + everyoneMarried);

        boolean isEveryoneMarried = people.stream().allMatch(Person::isMarried); //noneMatch and anyMatch
        System.out.println("Is everyone married: " + isEveryoneMarried);

        Optional<Person> firstMarried = people.stream()
                .filter(Person::isMarried)
                .findFirst(); //vs findAny
        System.out.println("First married: " + firstMarried);


        ____________("Peek");
        String joinedAfterPeek = people.stream()
                .peek(System.out::println) //debugging only
                .filter(Person::isMarried)
                .map(Person::getFullName)
                .collect(joining(", "));
        System.out.println(joinedAfterPeek);
    }
}
