package com.ss.touragency.controller.city;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.constants.PathToJsp;
import com.ss.touragency.constants.PathToPage;
import com.ss.touragency.entity.City;
import com.ss.touragency.entity.Country;
import com.ss.touragency.entity.Hotel;
import com.ss.touragency.util.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(PathToPage.CITY_INFO)
public class CityInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Country> countryList = Context.getInstance().getCountryService().getCountryList();
        req.setAttribute("country", countryList);

        List<City> cityList = Context.getInstance().getCityService().getCityList();
        req.setAttribute("city", cityList);

        List<Hotel> hotelList = Context.getInstance().getHotelService().getHotelList();
        req.setAttribute("hotel", hotelList);

        req.getRequestDispatcher(PathToJsp.CITY_JSP).forward(req, resp);


    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) {

        if (request.getSession().getAttribute(Attribute.CITY_ID) == null) {



        }
    }
}
