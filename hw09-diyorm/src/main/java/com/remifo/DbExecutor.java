package com.remifo;

import com.remifo.h2.H2demo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class DbExecutor<T> {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(H2demo.class);
    private final Connection connection;

    public DbExecutor() throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
        try (PreparedStatement pst = connection.prepareStatement(
                "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")
        ) {
            pst.executeUpdate();
        }
    }

    void create(T objectData) throws SQLException {
        try (PreparedStatement pst = connection.prepareStatement("insert into user(name, age) values (?, ?)")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            pst.setString(1, "Roman");
            pst.setInt(2, 20);
            try {
                int rowCount = pst.executeUpdate(); //Блокирующий вызов
                this.connection.commit();
                logger.info("inserted rowCount: {}", rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    void update(T objectData) {

    }

    void createOrUpdate(T objectData) {

    } // опционально.

    T load(long id, Class<T> clazz) {
        throw new UnsupportedOperationException();
    }

}
