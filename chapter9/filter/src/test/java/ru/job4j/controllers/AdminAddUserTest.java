package ru.job4j.controllers;

import org.junit.Test;
import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

/**
 * AdminAddUser controller test.
 *
 * @author Alexey Voronin.
 * @since 17.07.2017.
 */
public class AdminAddUserTest {

    /**
     * Settings.
     */
    private final Settings settings = new Settings();

    /**
     * User dao.
     */
    private final UserDao userDao = new UserToDB(PoolDataSource.setupDataSource(settings.getValue("url"),
            settings.getValue("name"), settings.getValue("password")));

    /**
     * UpdateUser.
     *
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Test
    public void adminAddUser() throws ServletException, IOException {
        final AdminAddUser addUser = new AdminAddUser();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");
        when(request.getParameter("email")).thenReturn("admin@admin.ru");
        when(request.getParameter("role")).thenReturn("1");

        addUser.init();
        addUser.doPost(request, response);

        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("role");

        final List<User> users = userDao.getAllUsers();
        User user = null;
        for (User u : users) {
            if ("admin".equalsIgnoreCase(u.getLogin()) && "admin".equalsIgnoreCase(u.getPassword())) {
                user = u;
            }
        }
        this.userDao.deleteUserByID(user.getId());

        assertThat(user.getLogin(), is("admin"));
        assertThat(user.getPassword(), is("admin"));
        assertThat(user.getEmail(), is("admin@admin.ru"));
    }
}