package ru.job4j.servlets;

import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Main page servlet.
 *
 * @author Alexey Voronin.
 * @since 09.07.2017.
 */
public class MainPage extends HttpServlet {

    /**
     * UserDao.
     */
    private UserDao userDao;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        StringBuilder sb = new StringBuilder();
        sb.append("<!DOCTYPE html>")
                .append("<head>")
                .append("<title></title>").append("<h1>Main page</h1>").append("</head>")
                .append("<body>").append(String.format("<form action = %s/add method = get>", req.getContextPath()))
                .append("<input type = 'submit' value = 'Add new user'/>").append("</form>").append("<br/>")
                .append("<table border = '1'>")
                .append("<tr>")
                .append("<th>ID</th>")
                .append("<th>name</th>")
                .append("<th>login</th>")
                .append("<th>email</th>")
                .append("<th>create_date</th>")
                .append("<th>Edit</th>")
                .append("<th>Delete</th>");
        for (User user : userDao.getAllUsers()) {
            String edit = String.format("<form action=%s/update method=get><input type='hidden' name='id' value='%s'/>%s",
                    req.getContextPath(), user.getId(), "<input type='submit' value='Edit'/></form>");
            String delete = String.format("<form action=%s/delete method=post><input type='hidden' name='id' value='%s'/>%s",
                    req.getContextPath(), user.getId(), "<input type='submit' value='Delete'/></form>");
            sb.append("<tr>")
                    .append(String.format("<td>%s</td>", user.getId()))
                    .append(String.format("<td>%s</td>", user.getName()))
                    .append(String.format("<td>%s</td>", user.getLogin()))
                    .append(String.format("<td>%s</td>", user.getEmail()))
                    .append(String.format("<td>%s</td>", user.getCreateDate()))
                    .append(String.format("<td>%s</td>", edit))
                    .append(String.format("<td>%s</td>", delete))
                    .append("</tr>");
        }
        sb.append("</table>").append(String.format("<form action = %s/add method = get>", req.getContextPath()))
                .append("</body>").append("</html>");
        try (PrintWriter writer = new PrintWriter(resp.getOutputStream())) {
            writer.append(sb.toString());
            writer.flush();
        }
    }


    @Override
    public void init() throws ServletException {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Settings settings = new Settings();
        this.userDao = new UserToDB(PoolDataSource.setupDataSource(
                settings.getValue("url"), settings.getValue("name"), settings.getValue("password")));
    }

}
