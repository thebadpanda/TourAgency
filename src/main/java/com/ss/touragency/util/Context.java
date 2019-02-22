package com.ss.touragency.util;

import com.ss.touragency.dao.*;
import com.ss.touragency.service.*;

public class Context {

    private static volatile Context instance;

    CountryDao countryDao;
    CityDao cityDao;
    HotelDao hotelDao;
    ClientDao clientDao;
    VisaDao visaDao;
    OrderDetailsDao orderDetailsDao;

    CountryService countryService;
    HotelService hotelService;
    OrderDetailsService orderDetailsService;
    CityService cityService;
    ClientService clientService;
    VisaService visaService;

    public CountryService getCountryService() {
        return countryService;
    }

    public HotelService getHotelService() {
        return hotelService;
    }

    public OrderDetailsService getOrderDetailsService() {
        return orderDetailsService;
    }

    public ClientService getClientService(){return clientService;}

    public CityService getCityService() {
        return cityService;
    }

    public VisaService getVisaService() {return visaService; }

    public VisaDao getVisaDao(){return visaDao; }

    public CountryDao getCountryDao(){return countryDao;}

    public ClientDao getClientDao(){return clientDao; }

    private Context() {
        initComponents();
    }

    private void initComponents() {
        countryDao = new CountryDao();
        cityDao = new CityDao();
        hotelDao = new HotelDao();
        clientDao = new ClientDao();
        visaDao = new VisaDao();
        orderDetailsDao = new OrderDetailsDao();

        countryService = new CountryService();
        hotelService = new HotelService();
        orderDetailsService = new OrderDetailsService();
        cityService = new CityService();
        clientService = new ClientService();
        visaService = new VisaService();

    }

    public static Context getInstance() {
        if (instance == null) {
            synchronized (Context.class) {
                if (instance == null) {
                    instance = new Context();
                }
            }
        }
        return instance;
    }
}
