package com.ss.touragency;

import com.ss.touragency.dao.DBConnection;

import java.sql.SQLException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        System.out.println( "Hello World!" );
        DBConnection.getDbConnection();
    }
}
