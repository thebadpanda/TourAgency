package com.ss.touragency.dao;

import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.Client;
import com.ss.touragency.entity.Country;
import com.ss.touragency.entity.Hotel;
import com.ss.touragency.entity.Visa;
import com.ss.touragency.util.Context;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VisaDao implements ICrudDao<Visa> {
    @Override
    public void insert(Visa visa) throws SQLException {
        String insertHotel = "INSERT INTO visa(Client_idClient,Country_idCountry) VALUES(?,?)";
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            PreparedStatement preparedStatement = null;

            preparedStatement = connection.prepareStatement(insertHotel);

            preparedStatement.setLong(1, visa.getClient().getIdClient());
            preparedStatement.setLong(2, visa.getCountry().getIdCountry());

            preparedStatement.executeUpdate();

        }
    }

    @Override
    public List<Visa> selectAll() {
        List<Visa> visaList = new ArrayList<>();

        String sql = "SELECT Client_idClient, Country_idCountry FROM VISA";
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            statement = DBConnection.getDbConnection().createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {

                Visa visa = new Visa();
                ClientDao clientDao = new ClientDao();
                visa.setClient(clientDao.selectById(resultSet.getLong("Client_idClient")));
                CountryDao countryDao = new CountryDao();
                visa.setCountry(countryDao.selectById(resultSet.getLong("Country_idCountry")));
                visaList.add(visa);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


        return visaList;
    }

    @Override
    public Visa selectById(Long id) {
        Connection connection = DBConnection.getDbConnection();
        String sql = "SELECT Client_idClient, Country_idCountry FROM VISA WHERE idVisa=" + "'" + id + "'";
        Statement statement;
        ResultSet resultSet;
        Visa visa = null;

        try {
            statement = connection.createStatement();
            resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                visa = new Visa();
                ClientDao clientDao = new ClientDao();
                visa.setClient(clientDao.selectById(resultSet.getLong("Client_idClient")));
                CountryDao countryDao = new CountryDao();
                visa.setCountry(countryDao.selectById(resultSet.getLong("Country_idCountry")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return visa;
    }

    @Override
    public void updateById(Visa visa, Long id) {
        String sqlUpdate = "UPDATE VISA SET Client_idClient=?, Country_idCountry=? " + " WHERE idVisa=" + id + "";
        PreparedStatement preparedStatement = null;
        Connection connection = DBConnection.getDbConnection();
        if (connection != null) {

            try {

                preparedStatement = connection.prepareStatement(sqlUpdate);

                preparedStatement.setLong(1, visa.getClient().getIdClient());
                preparedStatement.setLong(2, visa.getCountry().getIdCountry());
                preparedStatement.setLong(3, visa.getId());

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Long id) {

    }

    public List<Country> selectCountryByIdUser(Long id) throws SQLException, NullPointerException {

        List<Country> countryList = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection();
        String sql = "select country.idCountry, country.countryName from client left join visa on client.idClient=visa.Client_idClient join country on " +
                "visa.Country_idCountry=country.idCountry where client.idClient=" + id + "";

        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        while(resultSet.next()){
            Country country = new Country();
            country.setIdCountry(resultSet.getLong("idCountry"));
            country.setCountryName(resultSet.getString("countryName"));
            countryList.add(country);
        }

        if(countryList == null){
            throw new NullPointerException("No visa for such user");
        }
        return countryList;
    }

    public List<Country> selectWitoutCertainCountry(List<Country> countryWithout){

        List<Country> countryAvailable = new ArrayList<>();

        countryAvailable.removeAll(countryWithout);

        return countryAvailable;

    }

    public void createVisa(String countryName, Long id) throws SQLException {

        Client client = Context.getInstance().getClientDao().selectById(id);
        System.out.println(client);
        Country country = Context.getInstance().getCountryDao().selectByName(countryName);
        System.out.println(country);
        Visa visa = new Visa(client,country);
        System.out.println(visa);
        insert(visa);
    }

    public void deleteVisaByClientIdAndCountryId(Long idClient, Long idCountry) throws SQLException {

        Connection connection = DBConnection.getDbConnection();
        String sql = "delete from visa where visa.Client_idClient=? and visa.Country_idCountry=?";

        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setLong(1, idClient);
        preparedStatement.setLong(2, idCountry);

        preparedStatement.executeUpdate();

    }

}
