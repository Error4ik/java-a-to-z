package ru.job4j.controllers;

import ru.job4j.models.User;
import ru.job4j.repository.UserRepository;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Register user controller.
 *
 * @author Alexey Voronin.
 * @since 14.11.2017.
 */
public class RegistrationController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        String email = req.getParameter("regEmail");
        String password = req.getParameter("regPassword");
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(password);
        user.setId(new UserRepository().save(user));
        HttpSession session = req.getSession();
        session.setAttribute("user", user);
        resp.sendRedirect("index.html");
    }
}
