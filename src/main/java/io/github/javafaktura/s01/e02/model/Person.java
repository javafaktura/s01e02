package io.github.javafaktura.s01.e02.model;

import com.github.javafaker.Faker;

import java.util.Random;

/**
 * @author Adam Król 2019-03-16
 */
public class Person {

    private String fullName;
    private boolean married;
    private int salary;
    private Sex sex;

    public static Person random() {
        Faker faker = new Faker();
        Person person = new Person();
        person.fullName = faker.name().fullName();
        person.married = new Random().nextBoolean();
        person.salary = faker.number().numberBetween(2100, 20000);
        person.sex = new Random().nextBoolean() ? Sex.MALE : Sex.FEMALE;
        return person;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public boolean isMarried() {
        return married;
    }

    public void setMarried(boolean married) {
        this.married = married;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    @Override
    public String toString() {
        return "Person{" +
                "fullName='" + fullName + '\'' +
                ", married=" + married +
                ", salary=" + salary +
                ", sex=" + sex +
                '}';
    }
}
