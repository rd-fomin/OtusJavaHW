package com.remifo.banknotes;

import java.util.Comparator;

public enum BanknoteDenomination implements Comparator<BanknoteDenomination> {
    Thousand(1000), FiveHundred(500), Hundred(100);

    private int denomination;

    BanknoteDenomination(int denomination) {
        this.denomination = denomination;
    }

    public int getDenomination() {
        return denomination;
    }

    @Override
    public int compare(BanknoteDenomination o1, BanknoteDenomination o2) {
        return Integer.compare(o1.getDenomination(), o2.getDenomination());
    }

    @Override
    public String toString() {
        return String.valueOf(denomination);
    }
}
