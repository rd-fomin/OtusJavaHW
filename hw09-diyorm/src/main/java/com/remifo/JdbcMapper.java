package com.remifo;

import com.remifo.test.User;

import java.sql.SQLException;

public class JdbcMapper<T> {
    private DbExecutor<T> dbExecutor;

    public JdbcMapper(Class<T> tClass) throws SQLException {
        super();
        dbExecutor = new DbExecutor<>(tClass);
    }

    void create(T t) throws SQLException, IllegalAccessException {
        dbExecutor.create(t);
    }

    void update(T t) throws SQLException, IllegalAccessException {
        dbExecutor.update(t);
    }

    void createOrUpdate(T t) throws SQLException, IllegalAccessException {
        dbExecutor.createOrUpdate(t);
    } // опционально.

    T load(long id, Class<T> clazz) throws SQLException {
        return dbExecutor.load(id, clazz);
    }


}
