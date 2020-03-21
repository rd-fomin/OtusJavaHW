package com.remifo.test;

import com.remifo.Id;

public class Account {

    @Id
    private long first;
    private String name;
    private int age;

    public Account() {

    }

    public Account(long first, String name, int age) {
        this.first = first;
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "Account{" +
                "first=" + first +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
