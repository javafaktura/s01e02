package io.github.javafaktura.s01.e02;

import java.util.Optional;

/**
 * @author Adam Król 2019-03-15
 */
class ExternalService {

    static Integer call() {
        return null;
    }

    static Optional<Integer> optionalCall() {
        return Optional.of(20);
    }

    static Optional<Integer> emptyCall() {
        return Optional.empty();
    }
}
