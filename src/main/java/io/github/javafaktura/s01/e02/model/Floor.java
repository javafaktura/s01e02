package io.github.javafaktura.s01.e02.model;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-03-16
 */

public class Floor {

    private int number;
    private List<Flat> flats;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Flat> getFlats() {
        return flats;
    }

    public void setFlats(List<Flat> flats) {
        this.flats = flats;
    }

    public static Floor random(int number, int flats) {
        Floor floor = new Floor();
        floor.setNumber(number);
        floor.setFlats(IntStream.range(0, flats).mapToObj(i -> Flat.random(i + 1, new Random().nextInt(6))).collect(toList()));
        return floor;
    }
}
