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
 * Login user controller.
 *
 * @author Alexey Voronin.
 * @since 13.11.2017.
 */
public class LoginController extends HttpServlet {

    private final UserRepository userRepository = new UserRepository();

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String login = req.getParameter("login");
        String password = req.getParameter("password");
        HttpSession session = req.getSession();
        User user = userRepository.getUserByEmailAndPass(login, password);
        if (user != null) {
            session.setAttribute("user", user);
            resp.sendRedirect("index.html");
        } else {
            req.setAttribute("error", "Credentials is not valid!");
            resp.sendRedirect("login.html");
        }
    }

    @Override
    public void init() throws ServletException {
        User user = this.userRepository.getUserByEmailAndPass("test", "test");
        if (user == null) {
            user = new User();
            user.setEmail("test");
            user.setPassword("test");
            user.setName("test");
            this.userRepository.save(user);
        }
    }
}
