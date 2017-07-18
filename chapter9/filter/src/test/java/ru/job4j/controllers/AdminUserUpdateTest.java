package ru.job4j.controllers;

import org.junit.Test;
import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

/**
 * Admin user update controller.
 *
 * @author Alexey Voronin.
 * @since 17.07.2017.
 */
public class AdminUserUpdateTest {

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
    public void updateUser() throws ServletException, IOException {
        final int userId = this.userDao.addUser(new User(0, "test", "test", "test", new Date(), new Role(1, "admin")));
        final AdminUserUpdate userUpdate = new AdminUserUpdate();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn(String.format("%s", userId));
        when(request.getParameter("login")).thenReturn("testLogin");
        when(request.getParameter("password")).thenReturn("testPass");
        when(request.getParameter("email")).thenReturn("testEmail");
        when(request.getParameter("role")).thenReturn("1");

        userUpdate.init();
        userUpdate.doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("email");
        verify(request, atLeast(1)).getParameter("role");

        final User user = this.userDao.getUserByID(userId);
        this.userDao.deleteUserByID(userId);

        assertThat(user.getLogin(), is("testLogin"));
        assertThat(user.getPassword(), is("testPass"));
        assertThat(user.getEmail(), is("testEmail"));
    }
}