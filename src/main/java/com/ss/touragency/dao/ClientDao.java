package com.ss.touragency.dao;

import com.ss.touragency.dbConnection.DBConnection;
import com.ss.touragency.entity.Client;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao implements ICrudDao<Client> {

    @Override
    public void insert(Client client) throws SQLException {

        Connection connection = DBConnection.getDbConnection();
        if (connection != null) {

            String insertClient = "insert into client(clientName, clientSurname, phoneNumber, clientLogin, clientPassword) values(?,?,?,?,?)";
            PreparedStatement preparedStatement;

            preparedStatement = connection.prepareStatement(insertClient);

            preparedStatement.setString(1, client.getClientName());
            preparedStatement.setString(2, client.getClientSurname());
            preparedStatement.setString(3, client.getPhoneNumber());
            preparedStatement.setString(4, client.getClientLogin());
            preparedStatement.setString(5, client.getClientPassword());

            preparedStatement.execute();

        }

    }

    @Override
    public List<Client> selectAll() {

        Connection connection = DBConnection.getDbConnection();
        List<Client> clientList = new ArrayList<>();

        if (connection != null) {

            Statement statement;
            ResultSet resultSet;
            String selectClient = "select clientName, clientSurname, phoneNumber from client";

            try {
                statement = connection.createStatement();
                resultSet = statement.executeQuery(selectClient);

                while (resultSet.next()) {

                    Client client = new Client();
                    client.setClientName(resultSet.getString("clientName"));
                    client.setClientSurname(resultSet.getString("clientSurname"));
                    client.setPhoneNumber(resultSet.getString("phoneNumber"));
                    client.setClientLogin(resultSet.getString("clientLogin"));
                    client.setClientPassword(resultSet.getString("clientPassword"));

                    clientList.add(client);
                }
            } catch (SQLException e) {
                System.out.println("Cant read list of clients from DB");
            }
        }

        return clientList;
    }

    @Override
    public Client selectById(Long id) {

        String selectClientById = "select clientName, clientSurname, phoneNumber, clientLogin, clientPassword from client where idClient=?";
        Connection connection = DBConnection.getDbConnection();
        ResultSet resultSet;
        Client client = null;
        if (connection != null) {

            PreparedStatement preparedStatement;
            try {
                preparedStatement = connection.prepareStatement(selectClientById);
                preparedStatement.setLong(1, id);

                resultSet = preparedStatement.executeQuery();

                while (resultSet.next()) {

                    client = new Client();
                    client.setIdClient(id);
                    client.setClientName(resultSet.getString("clientName"));
                    client.setClientSurname(resultSet.getString("clientSurname"));
                    client.setPhoneNumber(resultSet.getString("phoneNumber"));
                    client.setClientLogin(resultSet.getString("clientLogin"));
                    client.setClientPassword(resultSet.getString("clientPassword"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return client;
    }

    @Override
    public void updateById(Client client, Long id) {

        String updateClientById = "UPDATE client set clientName=?, clientSurname=?, phoneNumber=? where idClient=?";

        Connection connection = DBConnection.getDbConnection();

        if (connection != null) {

            PreparedStatement preparedStatement;

            try {
                preparedStatement = connection.prepareStatement(updateClientById);

                preparedStatement.setString(1, client.getClientName());
                preparedStatement.setString(2, client.getClientSurname());
                preparedStatement.setString(3, client.getPhoneNumber());
                preparedStatement.setLong(4, id);

                preparedStatement.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    @Override
    public void deleteById(Long id) {

        Connection connection = DBConnection.getDbConnection();
        String deleteClientById = "delete from client where idClient=?";

        if (connection != null) {

            PreparedStatement preparedStatement;

            try {
                preparedStatement = connection.prepareStatement(deleteClientById);

                preparedStatement.setLong(1, id);
                preparedStatement.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }

    }

    public Client findClientByNameAndPhone(String login, String password) throws NullPointerException {

        Client client = null;
        Connection connection = DBConnection.getDbConnection();
        String findClientSQL = "select idClient, clientName, clientSurname, phoneNumber, clientLogin, clientPassword from client where clientLogin='"
                + login + "'" + " AND clientPassword='" + password + "'";

        if (connection != null) {
            try {
                Statement statement = connection.createStatement();
                ResultSet resultSet = statement.executeQuery(findClientSQL);

                while (resultSet.next()) {

                    client = new Client();
                    client.setIdClient(resultSet.getLong("idClient"));
                    client.setClientName(resultSet.getString("clientName"));
                    client.setClientSurname(resultSet.getString("clientSurname"));
                    client.setPhoneNumber(resultSet.getString("phoneNumber"));
                    client.setClientLogin(resultSet.getString("clientLogin"));
                    client.setClientPassword(resultSet.getString("clientPassword"));

                }

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if (client == null) {
            throw new NullPointerException("Invalid login or password.");
        }
        return client;
    }

}
