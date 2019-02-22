package com.ss.touragency;

import com.ss.touragency.dao.CountryDao;
import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.Country;

import java.sql.SQLException;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws SQLException {
        DBConnection.getDbConnection();

        CountryDao c = new CountryDao();

        List<Country> list = c.selectAllCountries();

        System.out.println(c.selectAllCountries());

    }
}
