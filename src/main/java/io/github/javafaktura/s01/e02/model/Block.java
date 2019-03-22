package io.github.javafaktura.s01.e02.model;

import java.util.List;
import java.util.Random;
import java.util.stream.IntStream;

import static java.util.stream.Collectors.toList;

/**
 * @author Adam Król 2019-03-16
 */
public class Block {

    private int number;
    private List<Floor> floors;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public List<Floor> getFloors() {
        return floors;
    }

    public void setFloors(List<Floor> floors) {
        this.floors = floors;
    }

    public static Block random(int number, int floors) {
        Block block = new Block();
        block.setNumber(number);
        block.setFloors(IntStream.range(0, floors).mapToObj(i -> Floor.random(i + 1, new Random().nextInt(5))).collect(toList()));
        return block;
    }
}
