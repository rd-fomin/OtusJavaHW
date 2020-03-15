package com.remifo;

import java.util.ArrayList;
import java.util.List;

public class TestClass {
    private String name = "Hello";
    private int age = 23;
    private long time = 18_273_162_873L;
    private boolean hasHigherEducation = true;
    private float salary = 12_500.3f;
    private List<ArrayClass> arrayClasses = new ArrayList<>();

    public TestClass() {
        arrayClasses.add(null);
        arrayClasses.add(new ArrayClass("Node2", 12, 10_300_213L, true, 5_000.1f));
        arrayClasses.add(new ArrayClass("Node3", 15, 16_300_213L, false, 10_000.23f));
    }

    @Override
    public String toString() {
        return "TestClass{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", time=" + time +
                ", hasHigherEducation=" + hasHigherEducation +
                ", salary=" + salary +
                ", arrayClasses=" + arrayClasses +
                '}';
    }
}
