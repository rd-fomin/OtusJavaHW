package com.remifo.atm;

public class ATMIDGenerator {

    private static int ID = 0;

    private ATMIDGenerator() { }

    public static int getID() {
        return ++ID;
    }

}
