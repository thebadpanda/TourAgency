package com.ss.touragency.service;

import com.ss.touragency.dao.VisaDao;
import com.ss.touragency.entity.Country;
import com.ss.touragency.util.Context;
import java.sql.SQLException;
import java.util.List;

public class VisaService {

    public List<Country> selectCountryUser(Long id) throws SQLException {

        List<Country> countryList = Context.getInstance().getVisaDao().selectCountryByIdUser(id);

        return countryList;
    }

    //countries that havent user
    public List<Country> selectWithout(Long id) {

        List<Country> selectWith = null;
        try {
            selectWith = selectCountryUser(id);
        } catch (SQLException e) {
            System.out.println("No available countries for such user");
        }
        List<Country> selectAllCountries = Context.getInstance().getCountryDao().selectAll();

        selectAllCountries.removeAll(selectWith);

        return selectAllCountries;
    }

    public void createVisaForClient(String countryName, Long userId) throws SQLException {
        VisaDao visaDao = new VisaDao();
        visaDao.createVisa(countryName, userId);
    }

    public void deleteVisa(Long idClient, Long idCountry) throws SQLException {

        VisaDao visaDao = new VisaDao();
        visaDao.deleteVisaByClientIdAndCountryId(idClient, idCountry);

    }

}
