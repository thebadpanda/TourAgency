package com.ss.touragency.controller.registration;

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

@WebServlet(PathToPage.REGISTRATION_PATH)
public class RegistrationController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getRequestDispatcher(PathToJsp.REGISTRATION_JSP).forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if((req.getParameter("name")!= null && !req.getParameter("name").isEmpty())
                && (req.getParameter("surname") != null && !req.getParameter("surname").isEmpty())
                && (req.getParameter("phone") != null && !req.getParameter("phone").isEmpty())
                && (req.getParameter("login") != null && !req.getParameter("login").isEmpty())
                && (req.getParameter("password") != null && !req.getParameter("password").isEmpty())
                && (req.getParameter("password1") != null && !req.getParameter("password1").isEmpty())
                && (req.getParameter("password").equals(req.getParameter("password1")))){

            try {
                Context.getInstance().getClientService().registrationClient(req);
                resp.sendRedirect(PathToPage.LOGIN_PATH);
            }catch(SQLException e){
                req.setAttribute(Attribute.ERROR, "User with such phone number or login is already exists. Try again!");
                req.getRequestDispatcher(PathToJsp.REGISTRATION_JSP).forward(req, resp);
            }
        }

        else{
            req.setAttribute(Attribute.ERROR, "You dont enter the correct value. Try again!");
            req.getRequestDispatcher(PathToJsp.REGISTRATION_JSP).forward(req, resp);
        }


    }
}
