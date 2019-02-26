package com.ss.touragency.controller.orderdetails;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.constants.PathToJsp;
import com.ss.touragency.constants.PathToPage;
import com.ss.touragency.entity.Client;
import com.ss.touragency.entity.Hotel;
import com.ss.touragency.util.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

@WebServlet(PathToPage.ORDER_DETAILS)
public class OrderDetailsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("hotelName"));
        String hotelName = request.getParameter("hotelName");

        response.setContentType("application/json");
        try {
            Hotel hotel = Context.getInstance().getHotelService().getHotelByName(hotelName);
            request.setAttribute("hotel", hotel.getHotelName());

        } catch (SQLException e) {
            request.setAttribute(Attribute.ERROR, "Oops...Error show order details. Please try again");
        }
        request.getRequestDispatcher(PathToJsp.ORDER_JSP).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Client client = null;
        Hotel hotel = null;

        String beginDate = request.getParameter("beginDate");
        String endDate = request.getParameter("endDate");
        String hotelName = request.getParameter("data-hotelName");

        if (beginDate != null && endDate != null) {
            request.getSession().setAttribute(Attribute.BEGIN_DATE, beginDate);
            request.getSession().setAttribute(Attribute.END_DATE, endDate);
        }else{
            request.getRequestDispatcher(PathToJsp.ORDER_JSP).forward(request, response);
        }

        if (request.getParameter("data-hotelName") != null && !request.getParameter("data-hotelName").isEmpty()) {
            request.getSession().setAttribute(Attribute.HOTEL_NAME, hotelName);

            if (request.getSession().getAttribute(Attribute.CLIENT_ID) != null) {
                Long id = Long.parseLong((String) request.getSession().getAttribute(Attribute.CLIENT_ID));
                String hotelNamee = String.valueOf(request.getSession().getAttribute(Attribute.HOTEL_NAME));
                try {
                    hotel = Context.getInstance().getHotelService().getHotelByName(hotelNamee);
                } catch (SQLException e) {
                    request.setAttribute(Attribute.ERROR, "Oops...Error creating order details. Please try again");
                }
                client = Context.getInstance().getClientService().getClient(id);
            }
            String beginDateS = String.valueOf(request.getSession().getAttribute(Attribute.BEGIN_DATE));
            String endDateS = String.valueOf(request.getSession().getAttribute(Attribute.END_DATE));

            try {
                Context.getInstance().getOrderDetailsService().createOrder(client, hotel, beginDateS, endDateS);
            } catch (ParseException | SQLException | NullPointerException e) {
                request.setAttribute(Attribute.ERROR, "Oops...Error creating order details. Please try again" + e.getLocalizedMessage());
            }
            System.out.println(beginDate + endDate);
        }
        if (!response.isCommitted()){
            request.getRequestDispatcher(PathToJsp.ORDER_JSP).forward(request, response);
        }
    }
}