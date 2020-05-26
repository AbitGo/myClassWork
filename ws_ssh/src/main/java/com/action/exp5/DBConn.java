package com.action.exp5;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConn {
    String URL = "jdbc:mysql://localhost:3306/test?serverTimezone=UTC&allowMultiQueries=true";
    String USER = "root";
    String PASSWORD = "123456";
    Connection connection = null;
    Statement statement = null;

    public Statement getStatement() throws SQLException {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        connection = DriverManager.getConnection(URL, USER, PASSWORD);
        statement = connection.createStatement();
        return statement;
    }
}
