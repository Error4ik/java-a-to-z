package ru.job4j.controllers;

import ru.job4j.model.User;

import javax.servlet.ServletRequest;
import javax.servlet.Filter;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletResponse;
import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

/**
 * Authentication filter.
 *
 * @author Alexey Voronin.
 * @since 17.07.2017.
 */
public class AuthorizationFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) resp;
        if (request.getRequestURI().contains("/login")) {
            chain.doFilter(req, resp);
        } else {
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            if (user == null) {
                response.sendRedirect(String.format("%s/login", request.getContextPath()));
                return;
            }
            chain.doFilter(req, resp);
        }
    }

    @Override
    public void destroy() {

    }
}
