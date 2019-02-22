package com.ss.touragency.controller.visa;

import com.ss.touragency.constants.Attribute;
import com.ss.touragency.constants.PathToJsp;
import com.ss.touragency.constants.PathToPage;
import com.ss.touragency.util.Context;
import org.w3c.dom.Attr;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(PathToPage.VISA_PATH)
public class VisaController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute(Attribute.CLIENT_ID) != null) {
            Long id = Long.parseLong((String) req.getSession().getAttribute(Attribute.CLIENT_ID));

            req.setAttribute("countries", Context.getInstance().getVisaService().selectWithout(id));
        }
        req.getRequestDispatcher(PathToJsp.VISA_JSP).forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        if (req.getSession().getAttribute(Attribute.CLIENT_ID) != null) {

            Long id = Long.parseLong((String) req.getSession().getAttribute(Attribute.CLIENT_ID));
            String country = req.getParameter("countries");

            if (country.equals("All")) {
                req.setAttribute(Attribute.ERROR, "Select the country!");
                resp.sendRedirect(PathToPage.VISA_PATH);
            } else {
                try {
                    Context.getInstance().getVisaService().createVisaForClient(country, id);
                    if (!resp.isCommitted()) {
                        resp.sendRedirect(PathToPage.USER_INFO);
                    }
                } catch (SQLException e) {
                    req.setAttribute(Attribute.ERROR, "Some error while create visa...Maybe you haven't money?But you can try again.");
                    if(!resp.isCommitted()) {
                        req.getRequestDispatcher(PathToJsp.VISA_JSP).forward(req, resp);
                    }
                } catch (NullPointerException e) {
                    req.setAttribute(Attribute.ERROR, "Select the country!");
                    if(!resp.isCommitted()) {
                        req.getRequestDispatcher(PathToJsp.VISA_JSP).forward(req, resp);
                    }
                }
            }
        }


    }
}
