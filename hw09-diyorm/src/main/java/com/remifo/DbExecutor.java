package com.remifo;

import com.remifo.test.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Field;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DbExecutor<T> {
    private static final String URL = "jdbc:h2:mem:";
    private static Logger logger = LoggerFactory.getLogger(Object.class);
    private final Connection connection;
    private final String bdName;

    public DbExecutor(Class<T> tClass) throws SQLException {
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
        this.bdName = tClass.getSimpleName().toLowerCase();
        try (PreparedStatement pst = connection.prepareStatement(
                "create table " + bdName + "(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")
        ) {
            pst.executeUpdate();
        }
    }

    void create(T objectData) throws SQLException, IllegalAccessException {
        long id = -1;
        List<Object> list = new ArrayList<>();
        if (objectData != null) {
        Class<?> clazz = objectData.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    id = field.getLong(objectData);
                } else {
                    list.add(field.get(objectData));
                }
            }
        }
        if (id < 0) throw new SQLException();
        try (PreparedStatement pst = connection.prepareStatement("insert into " + bdName + "(id, name, age) values (?, ?, ?)")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            pst.setLong(1, id);
            pst.setString(2, (String) list.get(0));
            pst.setInt(3, (int) list.get(1));
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

    void update(T objectData) throws IllegalAccessException, SQLException {
        long id = -1;
        List<Object> list = new ArrayList<>();
        if (objectData != null) {
            Class<?> clazz = objectData.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    id = field.getLong(objectData);
                } else {
                    list.add(field.get(objectData));
                }
            }
        }
        if (id < 0) throw new SQLException();
        try (PreparedStatement pst = connection.prepareStatement("update " + bdName + " set name = ?, age = ? where id = ?")) {
            Savepoint savePoint = this.connection.setSavepoint("savePointName");
            pst.setString(1, (String) list.get(0));
            pst.setInt(2, (int) list.get(1));
            pst.setLong(3, id);
            try {
                int rowCount = pst.executeUpdate(); //Блокирующий вызов
                this.connection.commit();
                logger.info("updated rowCount: {}", rowCount);
            } catch (SQLException ex) {
                this.connection.rollback(savePoint);
                logger.error(ex.getMessage(), ex);
            }
        }
    }

    void createOrUpdate(T objectData) throws IllegalAccessException, SQLException {
        long id = -1;
        if (objectData != null) {
            Class<?> clazz = objectData.getClass();
            Field[] fields = clazz.getDeclaredFields();
            for (Field field : fields) {
                field.setAccessible(true);
                if (field.isAnnotationPresent(Id.class)) {
                    id = field.getLong(objectData);
                }
            }
        }
        if (id < 0) throw new SQLException();
        try (PreparedStatement pst = connection.prepareStatement("select id from " + bdName + " where id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next())
                    update(objectData);
                else
                    create(objectData);
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    } // опционально.

    T load(long id, Class<T> clazz) throws SQLException {
        T t = null;
        try {
            t = clazz.newInstance();
        } catch (InstantiationException | IllegalAccessException e) {
            e.printStackTrace();
        }
        Field[] fields = clazz.getDeclaredFields();
        try (PreparedStatement pst = this.connection.prepareStatement("select * from " + bdName + " where id = ?")) {
            pst.setLong(1, id);
            try (ResultSet rs = pst.executeQuery()) {
                if (rs.next()) {
                    for (Field field : fields) {
                        field.setAccessible(true);
                        if (field.isAnnotationPresent(Id.class)) {
                            field.setLong(t, rs.getLong(1));
                        } else if (field.getGenericType().getTypeName().equals("java.lang.String")) {
                            field.set(t, rs.getString(2));
                        } else if (field.getGenericType().equals(int.class)) {
                            field.setInt(t, rs.getInt(3));
                        }
                    }
                    return t;
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
