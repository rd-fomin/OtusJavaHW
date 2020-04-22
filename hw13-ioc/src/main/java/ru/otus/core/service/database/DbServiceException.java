package ru.otus.core.service.database;

public class DbServiceException extends RuntimeException {

    public DbServiceException(Exception e) {
        super(e);
    }

}
