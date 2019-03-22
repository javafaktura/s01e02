package io.github.javafaktura.s01.e02;

import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigInteger;
import java.util.Random;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import static io.github.javafaktura.s01.e02.util.Pretty.____________;

/**
 * @author Adam Król 2019-03-25
 */
public class A_DemoLambda {
    public static void main(String[] args) {
        ____________("Consumer");

        Consumer<String> consumer = s -> System.out.println(s);
        Consumer<String> asAMethodReference = System.out::println;

        consumer.accept("test");
        asAMethodReference.accept("test2");


        ____________("Supplier");
        Supplier<String> supplier = () -> "String2";
        Random random = new Random();
        Supplier<Integer> supplierAsMethodReference = random::nextInt;

        System.out.println(supplier.get());


        ____________("Function");
        Function<Integer, String> function = v -> {
            if (v != null) {
                return v.toString();
            } else {
                return "no value";
            }
        };
        Function<Integer, String> functionAsMethodReference = RandomStringUtils::random;

        System.out.println(function.apply(10));

        BinaryOperator<BigInteger> binaryOperator = (firstParam, secondParam) -> firstParam.add(secondParam); //BiFunction

        ____________("BinaryOperator");
        System.out.println(binaryOperator.apply(BigInteger.TEN, BigInteger.TWO));
    }
}
