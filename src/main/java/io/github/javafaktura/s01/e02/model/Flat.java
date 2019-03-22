package io.github.javafaktura.s01.e02.model;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-03-16
 */
public class Flat {

    private int number;
    private int size;
    private List<Person> residents;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public List<Person> getResidents() {
        return residents;
    }

    public void setResidents(List<Person> residents) {
        this.residents = residents;
    }

    public static Flat random(int number, int residents) {
        Flat flat = new Flat();
        flat.setNumber(number);
        flat.setSize(new Random().nextInt(100));
        flat.setResidents(IntStream.range(0, residents).mapToObj(i -> Person.random()).collect(toList()));
        return flat;
    }
}
