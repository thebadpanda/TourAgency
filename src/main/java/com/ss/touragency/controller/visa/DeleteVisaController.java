package com.ss.touragency.controller.visa;

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

@WebServlet(PathToPage.VISADELETE_PATH)
public class DeleteVisaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if(req.getSession().getAttribute(Attribute.CLIENT_ID) != null){

            Long idClient = Long.parseLong((String) req.getSession().getAttribute(Attribute.CLIENT_ID));
            Long idCountry = Long.parseLong((String) req.getParameter("idClient"));

            try {
                Context.getInstance().getVisaService().deleteVisa(idClient, idCountry);
            } catch (SQLException e) {
                req.setAttribute(Attribute.ERROR, "Oops...Error while delete visa. But you can try again");
                req.getRequestDispatcher(PathToJsp.VISA_JSP).forward(req, resp);
            }

            resp.sendRedirect(PathToPage.USER_INFO);

        }

    }

}
