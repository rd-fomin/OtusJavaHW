package com.remifo;

public class TestCount {

    private int numberSuccessfulTest;
    private int numberUnsuccessfulTest;

    public TestCount() {
        this.numberSuccessfulTest = 0;
        this.numberUnsuccessfulTest = 0;
    }

    public int getNumberSuccessfulTest() {
        return numberSuccessfulTest;
    }

    public void plusNumberSuccessfulTest() {
        this.numberSuccessfulTest++;
    }

    public int getNumberUnsuccessfulTest() {
        return numberUnsuccessfulTest;
    }

    public void plusNumberUnsuccessfulTest() {
        this.numberUnsuccessfulTest++;
    }

    @Override
    public String toString() {
        return '{' +
                "numberSuccessfulTest = " + numberSuccessfulTest +
                ", numberUnsuccessfulTest = " + numberUnsuccessfulTest +
                '}';
    }
}
