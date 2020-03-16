package com.remifo.test;

import com.remifo.Id;

public class User {

    @Id
    private int first;
    private String name;
    private int age;

    public User(int first, String name, int age) {
        this.first = first;
        this.name = name;
        this.age = age;
    }
}
