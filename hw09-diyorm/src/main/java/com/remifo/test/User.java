package com.remifo.test;

import com.remifo.Id;

public class User {

    @Id
    private long first;
    private String name;
    private int age;

    public User() {

    }

    public User(long first, String name, int age) {
        this.first = first;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "User{" +
                "first=" + first +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
