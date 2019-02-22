package com.ss.touragency.dao;

import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.Country;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CountryDao implements ICrudDao<Country> {

    @Override
    public void insert(Country country) throws SQLException {

        String insertCountry = "INSERT INTO country(countryName) VALUES(?)";
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(insertCountry);

            preparedStatement.setString(1, country.getCountryName());
            preparedStatement.execute();


        }
    }

    @Override
    public List<Country> selectAll() {
        List<Country> countryList = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection();

        String sql = "SELECT * FROM COUNTRY";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Country country = new Country();
                country.setIdCountry(resultSet.getLong("idCountry"));
                country.setCountryName(resultSet.getString("countryName"));

                countryList.add(country);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return countryList;
    }

    @Override
    public Country selectById(Long id) {

        String sql = "SELECT * FROM COUNTRY WHERE idCountry=" + "'" + id + "'";
        Statement statement;
        ResultSet resultSet;
        Country country = null;

        try {
            statement = DBConnection.getDbConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                country = new Country();

                country.setIdCountry(resultSet.getLong("idCountry"));
                country.setCountryName(resultSet.getString("countryName"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return country;

    }

    public Country selectByName(String name){

        String sql = "SELECT * FROM COUNTRY WHERE countryName=" + "'" + name + "'";
        Statement statement;
        ResultSet resultSet;
        Country country = null;

        try {
            statement = DBConnection.getDbConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                country = new Country();
                country.setCountryName(resultSet.getString("countryName"));
                country.setIdCountry(resultSet.getLong("idCountry"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return country;
    }

    @Override
    public void updateById(Country country, Long id) {

        String sqlUpdate = "UPDATE country SET countryName=?" + " WHERE idCountry=" + id + "";

        PreparedStatement preparedStatement;
        Connection connection = DBConnection.getDbConnection();
        if (connection != null) {

            try {

                preparedStatement = connection.prepareStatement(sqlUpdate);

                preparedStatement.setString(1, country.getCountryName());

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Long id) {

        String deleteQuery = "DELETE FROM country WHERE idCountry=" + id + "";

        Statement statement;
        try {
            statement = DBConnection.getDbConnection().createStatement();

            statement.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
