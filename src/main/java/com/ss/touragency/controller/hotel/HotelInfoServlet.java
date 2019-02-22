package com.ss.touragency.controller.hotel;

import com.ss.touragency.constants.PathToJsp;
import com.ss.touragency.constants.PathToPage;
import com.ss.touragency.entity.City;
import com.ss.touragency.entity.Country;
import com.ss.touragency.entity.Hotel;
import com.ss.touragency.util.Context;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(PathToPage.HOTEL_INFO)
public class HotelInfoServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Hotel> hotelList = Context.getInstance().getHotelService().getHotelList();
        request.setAttribute("hotel", hotelList);

        List<Country> countryList = Context.getInstance().getCountryService().getCountryList();
        request.setAttribute("country", countryList);

        List<City> cityList = Context.getInstance().getCityService().getCityList();
        request.setAttribute("city", cityList);

        request.getRequestDispatcher(PathToJsp.HOTEL_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("utf-8");

//        if(request.getParameter("data-hotelName") != null){
//            System.out.println(request.getAttribute("data-hotelName"));
//        }
        String countryName = request.getParameter("country");
        String cityName = request.getParameter("city");

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();
        JSONObject json = new JSONObject();

        if (countryName != null && cityName != null){

            if (countryName.equals("All") && cityName.equals("All")) {
                List<Country> countryList = Context.getInstance().getCountryService().getCountryList();
                request.setAttribute("country", countryList);
                List<City> cityList = Context.getInstance().getCityService().getCityList();
                request.setAttribute("city", cityList);
                List<Hotel> hotelList = Context.getInstance().getHotelService().getHotelList();
                request.setAttribute("hotel", hotelList);

                List<String> cityNameList = new ArrayList<>();
                for (City city : cityList) {
                    String cityNames = city.getCityName();
                    cityNameList.add(cityNames);
                }

                json.put("country", countryList);
                json.put("city", cityNameList);
                json.put("hotel", hotelList);
                out.print(json);
                out.flush();
            } else if (!countryName.equals("All") && cityName.equals("All")) {
                List<String> countryList = new ArrayList<>();
                Country country = Context.getInstance().getCountryService().getCountryByName(countryName);
                countryList.add(country.getCountryName());

                List<City> cityList = Context.getInstance().getCityService().getCityByCountry(country.getIdCountry());

                List<String> cityNameList = new ArrayList<>();
                List<Hotel> hotelList = new ArrayList<>();
                for (City city : cityList) {
                    Long cityId = city.getCityId();
                    String cityNames = city.getCityName();
                    List<Hotel> hotels = Context.getInstance().getHotelService().getHotelsByCity(cityId);
                    hotelList.addAll(hotels);
                    cityNameList.add(cityNames);
                }

                request.setAttribute("country", countryList);
                request.setAttribute("city", cityNameList);
                request.setAttribute("hotel", hotelList);

                json.put("country", countryList);
                json.put("city", cityNameList);
                json.put("hotel", hotelList);

                System.out.println(json);
                out.print(json);
                out.flush();

            } else if (countryName.equals("All") && !cityName.equals("All")) {
                List<Country> countryList = Context.getInstance().getCountryService().getCountryList();

                City city = Context.getInstance().getCityService().getCityByName(cityName);
                Long cityId = city.getCityId();

                List<String> cityNameList = new ArrayList<>();
                List<Hotel> hotelList = Context.getInstance().getHotelService().getHotelsByCity(cityId);
                cityNameList.add(cityName);

                request.setAttribute("country", countryList);
                request.setAttribute("city", cityNameList);
                request.setAttribute("hotel", hotelList);

                json.put("country", countryList);
                json.put("city", cityNameList);
                json.put("hotel", hotelList);

                out.print(json);
                out.flush();

            } else if (!countryName.equals("All") && !cityName.equals("All")) {
                List<Country> countryList = new ArrayList<>();
                Country country = Context.getInstance().getCountryService().getCountryByName(countryName);
                countryList.add(country);
                request.setAttribute("country", countryList);

                City city = Context.getInstance().getCityService().getCityByName(cityName);
                List<City> cityList = new ArrayList<>();
                cityList.add(city);
                request.setAttribute("city", cityList);

                List<Hotel> hotelList = new ArrayList<>();
                for (City cityEntity : cityList) {
                    Long cityId = cityEntity.getCityId();
                    List<Hotel> hotels = Context.getInstance().getHotelService().getHotelsByCity(cityId);
                    hotelList.addAll(hotels);
                }
                request.setAttribute("hotel", hotelList);

                json.put("country", countryList);
                json.put("city", cityList);
                json.put("hotel", hotelList);

                out.print(json);
                out.flush();
            }
        }


        if (!response.isCommitted()) {
            request.getRequestDispatcher(PathToJsp.HOTEL_JSP).forward(request, response);
        }
    }
}
