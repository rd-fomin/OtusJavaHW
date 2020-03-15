package com.remifo;

public class Main {
    public static void main(String[] args) {
        TestLoggingInterface testLogging = MyTest.createMyClass( new TestLogging() );
        testLogging.calculation(7, "Hello", true);
        testLogging.calculation( 5 );
        testLogging.calculation(  );
    }
}
