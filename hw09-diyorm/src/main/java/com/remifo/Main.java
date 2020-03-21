package com.remifo;

import com.remifo.test.Account;
import com.remifo.test.User;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            JdbcMapper<User> jdbcMapper = new JdbcMapper<>(User.class);
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
            JdbcMapper<Account> jdbcMapper2 = new JdbcMapper<>(Account.class);
            jdbcMapper2.create(new Account(1, "Roman", 18));
            jdbcMapper2.create(new Account(2, "Roma", 23));
            jdbcMapper2.create(new Account(3, "Rom", 29));
            System.out.println(jdbcMapper2.load(1, Account.class));
            System.out.println(jdbcMapper2.load(2, Account.class));
            System.out.println(jdbcMapper2.load(3, Account.class));
            jdbcMapper2.update(new Account(2, "R", 45));
            System.out.println(jdbcMapper2.load(2, Account.class));
            jdbcMapper2.createOrUpdate(new Account(1, "R", 45));
            System.out.println(jdbcMapper2.load(1, Account.class));
        } catch (SQLException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
