package com.remifo.banknotes;

import java.util.Comparator;

public enum Denomination implements Comparator<Denomination> {
    Thousand(1000), FiveHundred(500), Hundred(100);

    private int value;

    Denomination(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public int compare(Denomination o1, Denomination o2) {
        return Integer.compare(o1.getValue(), o2.getValue());
    }

    @Override
    public String toString() {
        return String.valueOf(value);
    }

}
