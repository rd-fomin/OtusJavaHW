package com.remifo;

public class JSONParseException extends RuntimeException {
    public JSONParseException(IllegalAccessException e) {
        super(e);
    }
}
