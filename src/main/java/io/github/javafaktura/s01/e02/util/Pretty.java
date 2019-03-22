package io.github.javafaktura.s01.e02.util;

import java.util.Collection;
import java.util.Map;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Pretty {

    /**
     * method call which looks kinda like a header in the code, then gets printed with a header-like decoration
     * - for easily associating a line-of-code with output
     */
    public static void ____________(String header){
        System.out.println("\n");
        System.out.println("##### " + header);
    }

    public static void printStream(Stream<?> stream) {
        stream.forEach(System.out::println);
    }

    public static void printStream(IntStream stream) {
        stream.forEach(System.out::println);
    }


    public static void verboseCollectionPrint(Collection<?> c) {
        System.out.println("Collection class: " + c.getClass().getCanonicalName() + ", size:" + c.size());
        c.forEach(System.out::println);
    }

    public static void verboseMapPrint(Map<?, ?> m) {
        System.out.println("Collection class: " + m.getClass().getCanonicalName() + ", size:" + m.size());
        m.forEach((key, value) -> System.out.println(key + " " + value));
    }
}