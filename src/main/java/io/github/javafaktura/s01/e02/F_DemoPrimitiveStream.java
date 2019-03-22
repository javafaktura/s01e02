package io.github.javafaktura.s01.e02;

import io.github.javafaktura.s01.e02.helper.ResidentialAreaHelper;

import java.util.stream.IntStream;

import static io.github.javafaktura.s01.e02.util.Pretty.____________;
import static io.github.javafaktura.s01.e02.util.Pretty.printStream;

/**
 * @author Adam Król 2019-03-20
 */
public class F_DemoPrimitiveStream {

    public static void main(String[] args) {
        //Primitive stream
        ____________("Primitive stream");
        newStream().forEach(i -> System.out.println(i)); //This is an int
        newStream().boxed().forEach(i -> System.out.println(i)); // This is an Integer


        //Primitive stream mapping
        ____________("Primitive stream mapping");
        IntStream mappedStream = newStream().map(i -> i + 1);
        printStream(mappedStream);


        //Lets say we want to have stream of object
        ____________("Advanced stream mapping");
        newStream().mapToObj(i -> ResidentialAreaHelper.randomArea(i));
    }


    private static IntStream newStream() {
        return IntStream.of(1, 2, 3, 4);
    }
}
