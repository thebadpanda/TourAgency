package com.ss.touragency.dao;

import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.OrderDetails;
import com.ss.touragency.entity.Visa;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderDetailsDao implements ICrudDao<OrderDetails> {


    @Override
    public void insert(OrderDetails orderDetails) throws SQLException{

        String insertOrderDetails = "insert into orderdetails(Client_idClient, Hotel_idHotel, beginDate, endDate) values (?,?,?,?)";
        Connection connection = DBConnection.getDbConnection();

        if(connection != null){
            //            try {
                PreparedStatement preparedStatement = connection.prepareStatement(insertOrderDetails);
                preparedStatement.setLong(1, orderDetails.getClient().getIdClient());
                preparedStatement.setLong(2, orderDetails.getHotel().getHotelId());
                preparedStatement.setDate(3, orderDetails.getBeginDate());
                preparedStatement.setDate(4, orderDetails.getEndDate());

                preparedStatement.executeUpdate();
//            } catch (SQLException e) {
//                e.printStackTrace();
//            }
        }
    }


    @Override
    public List<OrderDetails> selectAll() {

        List<OrderDetails> orderDetailsList = new ArrayList<>();
        Connection connection = DBConnection.getDbConnection();
        String selectAllOrderDetails = "select idOrder,Client_idClient, Hotel_idHotel, beginDate, endDate from orderdetails";

        if(connection != null){
            Statement statement = null;
            ResultSet resultSet = null;

            try {

                statement = connection.createStatement();
                resultSet = statement.executeQuery(selectAllOrderDetails);

                while (resultSet.next()) {

                    OrderDetails orderDetails = new OrderDetails();
                    ClientDao clientDao = new ClientDao();
                    HotelDao hotelDao = new HotelDao();

                    orderDetails.setId(resultSet.getLong("idOrder"));
                    orderDetails.setClient(clientDao.selectById(resultSet.getLong("Client_idClient")));
                    orderDetails.setHotel(hotelDao.selectById(resultSet.getLong("Hotel_idHotel")));
                    orderDetails.setBeginDate(resultSet.getDate("beginDate"));
                    orderDetails.setEndDate(resultSet.getDate("endDate"));

                    orderDetailsList.add(orderDetails);

                }
            } catch (SQLException e) {
                e.getStackTrace();
            }

        }

        return orderDetailsList;
    }

    @Override
    public OrderDetails selectById(Long id) {
        String sql = "SELECT Client_idClient, Hotel_idHotel, beginDate, endDate FROM orderdetails WHERE idOrder=" + "'" + id + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        OrderDetails orderDetails = new OrderDetails();
        Connection connection = DBConnection.getDbConnection();

        if(connection != null){

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    orderDetails = new OrderDetails();
                    ClientDao client = new ClientDao();
                    orderDetails.setClient(client.selectById(resultSet.getLong(2)));
                    HotelDao hotelDao = new HotelDao();
                    orderDetails.setHotel(hotelDao.selectById(resultSet.getLong(3)));
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return orderDetails;
    }

    public List<OrderDetails> selectOrderFromAllOrders(Long id) {
        List<OrderDetails> ordersList = new ArrayList<>();
        String sql = "SELECT idOrder,Client_idClient, Hotel_idHotel, beginDate, endDate FROM ORDERDETAILS WHERE Client_idClient=" + "'" + id + "'";
        Statement statement = null;
        ResultSet resultSet = null;
        OrderDetails orderDetails = null;
        Connection connection = DBConnection.getDbConnection();

        if(connection != null){

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {
                    orderDetails = new OrderDetails();
                    orderDetails.setId(resultSet.getLong("idOrder"));
                    ClientDao client = new ClientDao();
                    orderDetails.setClient(client.selectById(resultSet.getLong(2)));
                    HotelDao hotelDao = new HotelDao();
                    orderDetails.setHotel(hotelDao.selectById(resultSet.getLong(3)));
                    orderDetails.setBeginDate(resultSet.getDate("beginDate"));
                    orderDetails.setEndDate(resultSet.getDate("endDate"));
                    ordersList.add(orderDetails);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

        return ordersList;
    }


    @Override
    public void updateById(OrderDetails orderDetails, Long id) {
        String sqlUpdate = "UPDATE ORDERDETAILS SET Client_idClient=?, Hotel_idHotel=? " + " WHERE idOrder=" + id + "";
        PreparedStatement preparedStatement = null;
        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            try {

                preparedStatement = connection.prepareStatement(sqlUpdate);

                preparedStatement.setLong(1, orderDetails.getClient().getIdClient());
                preparedStatement.setLong(2, orderDetails.getHotel().getHotelId());
                preparedStatement.setLong(3, orderDetails.getId());

                preparedStatement.executeUpdate();


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void deleteById(Long id) {
        String deleteQuery = "DELETE FROM orderdetails WHERE idOrder=" + id + "";

        Statement statement;
        try {
            statement = DBConnection.getDbConnection().createStatement();

            statement.executeUpdate(deleteQuery);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
