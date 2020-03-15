package com.remifo.asserts;

public class AssertEqualsException extends RuntimeException {

    public AssertEqualsException() {
        super();
    }

    public AssertEqualsException(String message) {
        super(message);
    }

    public AssertEqualsException(String message, Throwable cause) {
        super(message, cause);
    }

    public AssertEqualsException(Throwable cause) {
        super(cause);
    }

}
