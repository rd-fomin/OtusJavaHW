package com.remifo;

import java.util.List;

public class ArrayClass {
    private String name;
    private int age;
    private List<Double> integers = List.of(1.15d, 3.6d, 4.4d, 7.0d);
    private long time;
    private List<String> list = null;
    private boolean hasHigherEducation;
    private float salary;

    public ArrayClass(String name, int age, long time, boolean hasHigherEducation, float salary) {
        this.name = name;
        this.age = age;
        this.time = time;
        this.hasHigherEducation = hasHigherEducation;
        this.salary = salary;
//        list.add("Helllll");
//        list.add("Hellooo");
//        list.add("Heeeell");
    }

    @Override
    public String toString() {
        return "ArrayClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", integers=" + integers +
                ", time=" + time +
                ", list=" + list +
                ", hasHigherEducation=" + hasHigherEducation +
                ", salary=" + salary +
                '}';
    }
}
