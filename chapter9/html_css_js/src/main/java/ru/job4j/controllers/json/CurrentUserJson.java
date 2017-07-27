package ru.job4j.controllers.json;

import com.fasterxml.jackson.databind.ObjectMapper;
import ru.job4j.model.User;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Current user json.
 *
 * @author Alexey Voronin.
 * @since 27.07.2017.
 */
public class CurrentUserJson extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/json");
        final HttpSession session = req.getSession();
        final User user = (User) session.getAttribute("currentUser");
        final PrintWriter writer = new PrintWriter(resp.getOutputStream());
        final ObjectMapper mapper = new ObjectMapper();
        writer.write(mapper.writeValueAsString(user));
        writer.flush();
    }
}
