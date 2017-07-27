package ru.job4j.controllers.json;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Log out.
 *
 * @author Alexey Voronin.
 * @since 27.07.2017.
 */
public class LogOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getSession().invalidate();
        resp.sendRedirect(String.format("%s/login", req.getContextPath()));
    }
}
