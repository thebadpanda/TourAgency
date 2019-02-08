package com.ss.touragency.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getDbConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mydb";
        String user = "root";
        String password = "admin";

        return DriverManager.getConnection(url, user, password);
    }



}
