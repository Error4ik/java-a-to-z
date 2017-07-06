package ru.job4j;

import org.apache.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * EchoServlet.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public class EchoServlet extends HttpServlet {
    /**
     * Logger.
     */
    public static final Logger LOG = Logger.getLogger(EchoServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append("Hello World");
            writer.flush();
        }
    }
}
