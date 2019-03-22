package io.github.javafaktura.s01.e02;

import java.util.List;
import java.util.Optional;

import static io.github.javafaktura.s01.e02.util.Pretty.____________;

/**
 * @author Adam Król 2019-03-15
 */
public class B_DemoOptional {

    public static void main(String[] args) {
//      Everything is cool when we know the value and we are managing our variables
        ____________("Local variables");
        Integer myValue = 10;
        System.out.println("I do know that this is not null: " + myValue.longValue());

//      What about external libraries
//        Integer externalValue = ExternalService.call();
//        System.out.println("I am sure that my ExternalService works. Proof: "
//                + externalValue.longValue());

//        Optional<Integer> optionalCall2 = Optional.of(ExternalService.call());
//        System.out.println("I have optional. What can go wrong. Value: " + optionalCall2.get());


//      Lets use Nullable Optional for this
        Optional<Integer> call = Optional.ofNullable(ExternalService.call());
//      What if we want to call get
//        System.out.println("Simple value get: " + call.get());
//        System.out.println("Simple value get:" + call.orElseThrow());
//      Do not change NPE to NSEE
//      http://mail.openjdk.java.net/pipermail/core-libs-dev/2016-April/040531.html

        ____________("Simple isPresent");
        if (call.isPresent()) {
            System.out.println("Value from external service is: " + call.get());
        } else {
            System.out.println("Value from external service is null");
        }
//      This is not correct way to use Optional because we can just use it with for example ternary operator


        ____________("Functional");
//      There was a method ifPresent, what about else case
        ExternalService.optionalCall().ifPresent(System.out::println);

//      Optional can be functional as well
//      New method optionalCall (J9)
        ExternalService.emptyCall().ifPresentOrElse(
                v -> System.out.println("Value from external service: " + v),
                () -> System.out.println("Value is empty"));


//      Let's say we want to operate on value
        ____________("Changing value");
        Integer optionalCall = ExternalService.optionalCall()
                .map(i -> i + 10)
                .orElse(-1); //Gives us an object not Optional

        Integer emptyInteger = ExternalService.emptyCall()
                .map(i -> i + 10)
                .orElse(-1);

        System.out.println("Values:");
        System.out.println(optionalCall);
        System.out.println(emptyInteger);


//      Quick filtering variable
        ____________("Filtering");
        Integer filteredInteger = ExternalService.optionalCall()
                .filter(i -> i > 10)
                .orElse(-1);
        System.out.println("Found variable: " + filteredInteger);


//      We can even chain more than one Optional (Since J9)
        ____________("Chaining");
        Integer chainedOptional = Optional.ofNullable(ExternalService.call())
                .or(ExternalService::emptyCall)
//                .or(ExternalService::optionalCall)
                .map(i -> i + 123)
                .orElse(-1);

        System.out.println("Chained value:" + chainedOptional);
    }

//  The bad way
    private void function(Optional<Integer> parameter) {

    }

//  The very bad way (no serialization, what is it?)
    private static class DomainModel {
        Optional<Integer> myField;
    }

//  The good way (but not always)
    private Optional<Integer> call(Integer paramter) {
        return Optional.ofNullable(paramter);
    }

//  We should return emptyList instead of Optional
    private Optional<List<Integer>> callList() {
        return Optional.empty();
    }
}
