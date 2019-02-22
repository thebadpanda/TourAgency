package com.ss.touragency.service;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.dao.ClientDao;
import com.ss.touragency.entity.Client;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;

public class ClientService {

    public boolean validationClient(HttpServletRequest request) throws NullPointerException {

        ClientDao clientDao = new ClientDao();
        Client client = clientDao.findClientByNameAndPhone(request.getParameter(Attribute.LOGIN), request.getParameter(Attribute.PASSWORD));

        if (client != null) {
            return true;
        }

        return false;
    }

    public String setClientID(HttpServletRequest request) throws NullPointerException {

        ClientDao clientDao = new ClientDao();
        Client client = clientDao.findClientByNameAndPhone(request.getParameter(Attribute.LOGIN), request.getParameter(Attribute.PASSWORD));

        if (client != null) {
            return client.getIdClient() + "";
        }
        return null;
    }

    public Client getClient(Long id) {
        ClientDao clientDao = new ClientDao();
        return clientDao.selectById(id);
    }

    private boolean isExistItem(Long id) {
        boolean result = false;
        try {
            ClientDao clientDao = new ClientDao();
            clientDao.selectById(id);
            result = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public void registrationClient(HttpServletRequest request) throws SQLException {

        Client client = new Client();
        client.setClientName(request.getParameter("name"));
        client.setClientSurname(request.getParameter("surname"));
        client.setPhoneNumber(request.getParameter("phone"));
        client.setClientLogin(request.getParameter("login"));
        client.setClientPassword(request.getParameter("password"));

        ClientDao clientDao = new ClientDao();
        clientDao.insert(client);
    }

}
