package com.ss.touragency.dao;

import com.ss.touragency.entity.Country;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CountryDao {

    Country country = null;

    public List<Country> selectAllCountries() throws SQLException {

        List<Country> countryList = new ArrayList<>();

        String sql = "SELECT * FROM COUNTRY";
        Statement statement = DBConnection.getDbConnection().createStatement();

        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){

            country = new Country();
            country.setIdCountry(resultSet.getInt("idCountry"));
            country.setCountryName(resultSet.getString("countryName"));

            countryList.add(country);
        }

        return countryList;

    }

}
