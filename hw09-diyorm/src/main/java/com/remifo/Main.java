package com.remifo;

import com.remifo.test.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            JdbcMapper<User> jdbcMapper = new JdbcMapper<>();
            jdbcMapper.create(new User(1, "Roman", 18));
            jdbcMapper.create(new User(2, "Roma", 23));
            jdbcMapper.create(new User(3, "Rom", 29));
            System.out.println(jdbcMapper.load(1, User.class));
            System.out.println(jdbcMapper.load(2, User.class));
            System.out.println(jdbcMapper.load(3, User.class));
            jdbcMapper.update(new User(2, "R", 45));
            System.out.println(jdbcMapper.load(2, User.class));
            jdbcMapper.createOrUpdate(new User(1, "R", 45));
            System.out.println(jdbcMapper.load(1, User.class));
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
