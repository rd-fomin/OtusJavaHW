package com.remifo.h2;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

/**
 * @author sergey
 * created on 01.10.18.
 */
public class H2demo {
  private static final String URL = "jdbc:h2:mem:";
  private static Logger logger = LoggerFactory.getLogger(H2demo.class);
  private final Connection connection;

  private H2demo() throws SQLException {
    this.connection = DriverManager.getConnection(URL);
    this.connection.setAutoCommit(false);
  }

  public static void main(String[] args) throws SQLException {
    H2demo demo = new H2demo();
    demo.createTable();
    int id = 1;
    demo.insertRecord();
    demo.selectRecord(id);
    demo.close();
  }

  private void createTable() throws SQLException {
    try (PreparedStatement pst = connection.prepareStatement(
            "create table user(id bigint(20) NOT NULL auto_increment, name varchar(255), age int(3))")
    ) {
      pst.executeUpdate();
    }
  }

  private void insertRecord() throws SQLException {
    try (PreparedStatement pst = connection.prepareStatement("insert into user(name, age) values (?, ?)")) {
      Savepoint savePoint = this.connection.setSavepoint("savePointName");
      pst.setString(1, "Roma");
      pst.setInt(2, 19);
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

  private void selectRecord(int id) throws SQLException {
    try (PreparedStatement pst = this.connection.prepareStatement("select * from user where id = ?")) {
      pst.setInt(1, id);
      try (ResultSet rs = pst.executeQuery()) {
        StringBuilder outString = new StringBuilder();
        if (rs.next()) {
          outString.append(rs.getInt("id")).append(", ");
          outString.append(rs.getString("name")).append(", ");
          outString.append(rs.getInt("age"));
        }
        logger.info(outString.toString());
      }
    }
  }

  private void close() throws SQLException {
    this.connection.close();
  }

}
