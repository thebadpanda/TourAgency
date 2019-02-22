package com.ss.touragency.service;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.dao.CountryDao;
import com.ss.touragency.entity.Country;

import javax.servlet.http.HttpServletRequest;
import java.sql.SQLException;
import java.util.List;

public class CountryService {

    public boolean createCountry(HttpServletRequest request) throws SQLException {
        boolean result = false;

        String countryName = request.getParameter(Attribute.COUNTRY_NAME);
        if (countryName != null && !countryName.isEmpty()) {
            CountryDao countryDao = new CountryDao();
            Country country = new Country(1L, countryName);
            countryDao.insert(country);
            result = true;
        }

        return result;
    }

    public boolean updateCountry(HttpServletRequest request) {
        boolean result = false;
        CountryDao countryDao = new CountryDao();

        if (request.getParameter(Attribute.COUNTRY_NAME) != null) {
            Long countryId = Long.parseLong(request.getParameter(Attribute.COUNTRY_ID));
            Country country = countryDao.selectById(countryId);
            country.setCountryName(request.getParameter(Attribute.COUNTRY_NAME));
            countryDao.updateById(country, countryId);
            result = true;
        }
        return result;
    }

    public Country getCountryById(HttpServletRequest request) {
        CountryDao countryDao = new CountryDao();

        if (request.getSession().getAttribute(Attribute.COUNTRY_ID) != null &&
                isExistItem(Long.parseLong((String) request.getSession().getAttribute(Attribute.COUNTRY_ID)))) {
            Long countryId = Long.parseLong((String) request.getSession().getAttribute(Attribute.COUNTRY_ID));

            return countryDao.selectById(countryId);
        }
        return null;
    }

    public Country getCountryByName(String countryName) {
        CountryDao countryDao = new CountryDao();
        return countryDao.selectByName(countryName);
    }

    private boolean isExistItem(Long id) {
        boolean result = false;
        try {
            CountryDao countryDao = new CountryDao();
            countryDao.selectById(id);
            result = true;
        } catch (RuntimeException e) {
            e.printStackTrace();
        }
        return result;
    }

    public List<Country> getCountryList() {
        CountryDao countryDao = new CountryDao();
        return countryDao.selectAll();
    }
}
