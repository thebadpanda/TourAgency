package com.ss.touragency.controller.city;


import com.ss.touragency.constants.Attribute;
import com.ss.touragency.constants.PathToJsp;
import com.ss.touragency.constants.PathToPage;
import com.ss.touragency.util.Context;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(PathToPage.CREATE_CITY)
public class CityCreateServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getSession().getAttribute(Attribute.CITY) == null) {

            try {
                if (Context.getInstance().getCityService().createCity(request)) {
                    response.sendRedirect(PathToPage.CREATE_CITY);
                } else {
                    request.setAttribute(Attribute.ERROR, "Something went wrong! Please try again");
                    request.getRequestDispatcher(PathToJsp.CITY_JSP).forward(request, response);
                }
            } catch (SQLException e) {
                System.out.println("Such country is already exists!");
            }

        } else {
            request.setAttribute(Attribute.ERROR, "You are not login!");
            request.getRequestDispatcher(PathToJsp.LOGIN_JSP).forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (request.getSession().getAttribute(Attribute.CITY_ID) == null ){
            request.getRequestDispatcher(PathToJsp.CITY_JSP).forward(request, response);
        }
        else {
            request.setAttribute(Attribute.ERROR, "You are not login!");
            request.getRequestDispatcher(PathToJsp.LOGIN_JSP).forward(request, response);
        }


    }
}
