package com.ss.touragency.dao;

import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.Hotel;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class HotelDao implements ICrudDao<Hotel> {
    @Override
    public void insert(Hotel hotel) throws SQLException {
        String insertHotel = "INSERT INTO hotel(hotelName,City_idCity,availableCount) VALUES(?,?,?)";
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            PreparedStatement preparedStatement = connection.prepareStatement(insertHotel);

            preparedStatement.setString(1, hotel.getHotelName());
            preparedStatement.setLong(2, hotel.getCity().getCityId());
            preparedStatement.setInt(3, hotel.getAvailableCount());

            preparedStatement.execute();


        }
    }

    @Override
    public List<Hotel> selectAll() {
        List<Hotel> hotelList = new ArrayList<>();
        String sql = "SELECT idHotel, hotelName, City_idCity, availableCount FROM HOTEL";
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    CityDao city = new CityDao();

                    hotel.setHotelId(resultSet.getLong("idHotel"));
                    hotel.setHotelName(resultSet.getString("hotelName"));
                    hotel.setCity(city.selectById(resultSet.getLong("City_idCity")));
                    hotel.setAvailableCount(resultSet.getInt("availableCount"));

                    hotelList.add(hotel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return hotelList;
    }

    public Hotel selectByName(String name) throws SQLException {

        String sql = "select idHotel, hotelName, City_idCity, availableCount from hotel where hotelName=" + "'" + name + "'";
        Connection connection = DBConnection.getDbConnection();

        Hotel hotel = null;

        if (connection != null) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

            while (resultSet.next()) {
                hotel = new Hotel();
                hotel.setHotelId(resultSet.getLong("idHotel"));
                hotel.setHotelName(resultSet.getString("hotelName"));
                CityDao city = new CityDao();
                hotel.setCity(city.selectById(resultSet.getLong(3)));
                hotel.setAvailableCount(resultSet.getInt("availableCount"));
            }
        }
        return hotel;
    }

    @Override
    public Hotel selectById(Long id) throws SQLException {
        String sql = "SELECT idHotel, hotelName, City_idCity, availableCount FROM HOTEL WHERE idHotel=" + "'" + id + "'";

        Connection connection = DBConnection.getDbConnection();

        Hotel hotel = null;

        if (connection != null) {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    hotel = new Hotel();
                    hotel.setHotelId(resultSet.getLong("idHotel"));
                    hotel.setHotelName(resultSet.getString("hotelName"));
                    CityDao city = new CityDao();
                    hotel.setCity(city.selectById(resultSet.getLong(3)));
                    hotel.setAvailableCount(resultSet.getInt("availableCount"));
                }

        }

        return hotel;
    }

    public List<Hotel> selectByCityId(Long cityId) {
        String sql = "SELECT idHotel, hotelName, City_idCity, availableCount FROM HOTEL WHERE City_idCity=" + "'" + cityId + "'";
        Connection connection = DBConnection.getDbConnection();
        Statement statement;
        ResultSet resultSet;
        List<Hotel> hotelList = new ArrayList<>();

        if (connection != null) {
            try {
                statement = DBConnection.getDbConnection().createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    Hotel hotel = new Hotel();
                    hotel.setHotelId(resultSet.getLong("idHotel"));
                    hotel.setHotelName(resultSet.getString("hotelName"));
                    CityDao city = new CityDao();
                    hotel.setCity(city.selectById(resultSet.getLong("City_idCity")));
                    hotel.setAvailableCount(resultSet.getInt("availableCount"));
                    hotelList.add(hotel);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return hotelList;
    }

    @Override
    public void updateById(Hotel hotel, Long id) {
        String sqlUpdate = "UPDATE hotel SET hotelName=?,City_idCity=?,availableCount=?" + " WHERE idHotel=?";
        PreparedStatement preparedStatement = null;
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            try {

                preparedStatement = connection.prepareStatement(sqlUpdate);

                preparedStatement.setString(1, hotel.getHotelName());
                preparedStatement.setLong(2, hotel.getCity().getCityId());
                preparedStatement.setInt(3, hotel.getAvailableCount());
                preparedStatement.setLong(4, id);

                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Long id) {

    }
}
