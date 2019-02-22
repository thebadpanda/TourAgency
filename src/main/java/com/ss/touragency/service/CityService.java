package com.ss.touragency.service;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.dao.CityDao;
import com.ss.touragency.entity.City;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CityService {

    public boolean createCity(HttpServletRequest request) throws SQLException {
        boolean result = false;

        String cityName = request.getParameter(Attribute.CITY_NAME);

        if (cityName != null && !cityName.isEmpty()) {
            CityDao cityDao = new CityDao();
            City city = new City(1L, cityName);
            cityDao.insert(city);
            result = true;
        }

        return result;
    }

    public City getCity(HttpServletRequest request) {
        CityDao cityDao = new CityDao();

        if (request.getSession().getAttribute(Attribute.CITY_ID) != null &&
                isExistItem(Long.parseLong((String) request.getSession().getAttribute(Attribute.CITY_ID)))) {
            Long cityId = Long.parseLong((String) request.getSession().getAttribute(Attribute.CITY_ID));

            return cityDao.selectById(cityId);
        }
        return null;
    }

    public List<City> getCityList() {
        CityDao cityDao = new CityDao();
        return cityDao.selectAll();
    }

    public List<City> getCityByCountry(Long countryId) {
        CityDao cityDao = new CityDao();
//        if (request.getSession().getAttribute(Attribute.COUNTRY_ID) != null &&
//                isExistItem(Long.parseLong((String) request.getSession().getAttribute("country")))) {

//            Long countryId = Long.parseLong((String) request.getSession().getAttribute("country"));
            return cityDao.selectByCountryId(countryId);
//        }
//        return null;
    }

    public City getCityByName(String name){
        CityDao cityDao = new CityDao();

       return cityDao.selectCityByName(name);
    }

    private boolean isExistItem(Long id) {
        boolean result = false;
        try {
            CityDao cityDao = new CityDao();
            cityDao.selectById(id);
            result = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }


}
