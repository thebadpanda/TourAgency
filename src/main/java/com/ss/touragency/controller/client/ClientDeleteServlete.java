package com.ss.touragency.controller.client;

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

@WebServlet(PathToPage.ORDER_DELETE)
public class ClientDeleteServlete extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (Context.getInstance().getOrderDetailsService().deleteOrder(req)) {
            req.getSession().setAttribute(Attribute.ORDER_ID, null);
            resp.sendRedirect(PathToPage.USER_INFO);


        } else {
            req.setAttribute(Attribute.ERROR, "Oops, something went wrong... Try again!");
            req.getRequestDispatcher(PathToPage.USER_INFO).forward(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(PathToPage.USER_INFO);
    }
}
