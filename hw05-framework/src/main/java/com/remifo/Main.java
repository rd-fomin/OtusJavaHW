package com.remifo;

import com.remifo.asserts.AssertEqualsException;

public class Main {

    public static void main(String[] args) throws AssertEqualsException {
        TestFramework testFramework = new TestFramework();
        System.out.println(testFramework.startTest("com.remifo.ClassTest"));
    }

}
