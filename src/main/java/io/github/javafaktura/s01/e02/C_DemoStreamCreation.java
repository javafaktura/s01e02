package io.github.javafaktura.s01.e02;

import io.github.javafaktura.s01.e02.helper.ResidentialAreaHelper;
import io.github.javafaktura.s01.e02.model.Person;

import java.util.stream.Stream;

import static io.github.javafaktura.s01.e02.util.Pretty.____________;
import static io.github.javafaktura.s01.e02.util.Pretty.printStream;

/**
 * @author Adam Król 2019-03-16
 */
public class C_DemoStreamCreation {
    public static void main(String[] args) {
        //The simplest stream with array initializer
        ____________("Simple stream");
        Stream<Person> simplePersons = Stream.of(Person.random(), Person.random(), Person.random());
        printStream(simplePersons);


        //The most common used stream from collection
        ____________("Stream from collection");
        Stream<Person> collectionStream = ResidentialAreaHelper.randomPeople(10).stream();
        printStream(collectionStream);


        //Stream builder
        ____________("Stream builder");
        Stream<Person> streamBuilder = Stream.<Person>builder()
                .add(Person.random())
                .add(Person.random())
                .build();
        printStream(streamBuilder);


        //Stream with generate
        ____________("Stream generator");
//        Stream<Person> generated = Stream.generate(Person::random); //Infinite stream
        Stream<Person> generated = Stream.generate(Person::random).limit(10);
        printStream(generated);
    }
}
