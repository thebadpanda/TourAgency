package com.ss.touragency.service;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.dao.HotelDao;
import com.ss.touragency.entity.City;
import com.ss.touragency.entity.Hotel;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class HotelService {

    public boolean createHotel(Hotel hotel) throws SQLException {
        boolean result = false;

        String hotelName = hotel.getHotelName();
        int availableCount = hotel.getAvailableCount();
        City city = new City();

        if (hotelName != null && !hotelName.isEmpty()) {
            HotelDao hotelDao = new HotelDao();
            Hotel hotell = new Hotel(1L, hotelName, city, availableCount);
            hotelDao.insert(hotell);
            result = true;
        }

        return result;
    }

    public boolean updateHotel(HttpServletRequest request) throws SQLException {
        boolean result = false;
        HotelDao hotelDao = new HotelDao();

        if (request.getParameter(Attribute.HOTEL_NAME) != null) {
            Long hotelId = Long.parseLong(request.getSession().getAttribute(Attribute.HOTEL_ID).toString());
            Hotel hotel = hotelDao.selectById(hotelId);
            hotel.setHotelName(request.getParameter(Attribute.HOTEL_NAME));
//            hotel.setCity(request.getParameter(Attribute.CITY));
            hotel.setAvailableCount(Integer.parseInt(request.getParameter(Attribute.AVAILABLE_COUNT)));
            hotelDao.updateById(hotel, hotelId);
            result = true;
        }
        return result;
    }

    public Hotel getHotelByName(String name) throws SQLException {
        HotelDao hotelDao = new HotelDao();

        return hotelDao.selectByName(name);
    }

    public List<Hotel> getHotelList() {
        HotelDao hotelDao = new HotelDao();
        List<Hotel> hotelList = hotelDao.selectAll();
        return hotelList;
    }

    public List<Hotel> getHotelsByCity(Long cityId) {
        HotelDao hotelDao = new HotelDao();
        return hotelDao.selectByCityId(cityId);
    }


    private boolean isExistItem(Long id) throws SQLException {
        boolean result = false;
        try {
            HotelDao hotelDao = new HotelDao();
            hotelDao.selectById(id);
            result = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }


}
