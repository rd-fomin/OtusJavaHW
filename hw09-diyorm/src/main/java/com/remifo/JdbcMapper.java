package com.remifo;

import java.sql.SQLException;

public class JdbcMapper<T> extends DbExecutor<T> {
    T t;

    public JdbcMapper(T t) throws SQLException {
        super();
        this.t = t;
    }



}
