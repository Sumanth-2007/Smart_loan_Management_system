package model;

public class Person {
    private String name;
    private int age;

    public Person(String name, int age) {
        if(age < 18)
            throw new IllegalArgumentException("Age must be >= 18");

        this.name = name;
        this.age = age;
    }

    public String getName() { return name; }
    public int getAge() { return age; }
}