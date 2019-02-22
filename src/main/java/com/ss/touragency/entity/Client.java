package com.ss.touragency.entity;

import java.util.Objects;

public class Client {
    private Long idClient;
    private String clientName;
    private String clientSurname;
    private String phoneNumber;
    private String clientLogin;
    private String clientPassword;

    public Client(Long idClient, String clientName, String clientSurname, String phoneNumber, String clientLogin, String clientPassword) {
        this.idClient = idClient;
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.phoneNumber = phoneNumber;
        this.clientLogin = clientLogin;
        this.clientPassword = clientPassword;
    }

    public Client() {
    }

    public Client(String clientName, String clientSurname, String phoneNumber, String clientLogin, String clientPassword) {
        this.clientName = clientName;
        this.clientSurname = clientSurname;
        this.phoneNumber = phoneNumber;
        this.clientLogin = clientLogin;
        this.clientPassword = clientPassword;
    }

    public Long getIdClient() {
        return idClient;
    }

    public void setIdClient(Long idClient) {
        this.idClient = idClient;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientSurname() {
        return clientSurname;
    }

    public void setClientSurname(String clientSurname) {
        this.clientSurname = clientSurname;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getClientLogin() {
        return clientLogin;
    }

    public void setClientLogin(String clientLogin) {
        this.clientLogin = clientLogin;
    }

    public String getClientPassword() {
        return clientPassword;
    }

    public void setClientPassword(String clientPassword) {
        this.clientPassword = clientPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return idClient.equals(client.idClient) &&
                clientName.equals(client.clientName) &&
                clientSurname.equals(client.clientSurname) &&
                phoneNumber.equals(client.phoneNumber) &&
                clientLogin.equals(client.clientLogin) &&
                clientPassword.equals(client.clientPassword);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient, clientName, clientSurname, phoneNumber, clientLogin, clientPassword);
    }

    @Override
    public String toString() {
        return "Client{" +
                "idClient=" + idClient +
                ", clientName='" + clientName + '\'' +
                ", clientSurname='" + clientSurname + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                ", clientLogin='" + clientLogin + '\'' +
                ", clientPassword='" + clientPassword + '\'' +
                '}';
    }
}
