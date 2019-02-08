package com.ss.touragency.dbConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {

    public static Connection getDbConnection() throws SQLException {

        String url = "jdbc:mysql://localhost:3306/mydb" + "?useUnicode=true&useSSL=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String user = "root";
        String password = "admin";

        return DriverManager.getConnection(url, user, password);
    }



}
