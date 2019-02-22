package com.ss.touragency.entity;

import java.sql.Date;
import java.util.Objects;

public class OrderDetails {

    private Long id;
    private Client client;
    private Hotel hotel;
    private Date beginDate;
    private Date endDate;

    public OrderDetails(Long id, Client client, Hotel hotel, Date beginDate, Date endDate) {
        this.id = id;
        this.client = client;
        this.hotel = hotel;
        this.beginDate = beginDate;
        this.endDate = endDate;
    }

    public OrderDetails() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Date getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(Date beginDate) {
        this.beginDate = beginDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    public String toString() {
        return "OrderDetails{" +
                "client=" + client +
                ", hotel=" + hotel +
                ", beginDate=" + beginDate +
                ", endDate=" + endDate +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderDetails that = (OrderDetails) o;
        return id.equals(that.id) &&
                client.equals(that.client) &&
                hotel.equals(that.hotel) &&
                beginDate.equals(that.beginDate) &&
                endDate.equals(that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, client, hotel, beginDate, endDate);
    }
}
