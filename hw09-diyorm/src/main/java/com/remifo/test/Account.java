package com.remifo.test;

import com.remifo.Id;

public class Account {

    @Id
    private long no;
    private String type;
    private int rest;

    public Account() {

    }

    public Account(long no, String type, int rest) {
        this.no = no;
        this.type = type;
        this.rest = rest;
    }

    @Override
    public String toString() {
        return "Account{" +
                "no=" + no +
                ", type='" + type + '\'' +
                ", rest=" + rest +
                '}';
    }

}
