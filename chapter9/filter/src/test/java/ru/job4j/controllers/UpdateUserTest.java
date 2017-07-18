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
 * Update user controller test.
 *
 * @author Alexey Voronin.
 * @since 18.07.2017.
 */
public class UpdateUserTest {

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
        final int userId = this.userDao.addUser(new User(0, "test", "test", "test", new Date(), new Role(2, "user")));
        final UpdateUser updateUser = new UpdateUser();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        when(request.getParameter("id")).thenReturn(String.format("%s", userId));
        when(request.getParameter("login")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("user");
        when(request.getParameter("email")).thenReturn("user@user.ru");

        updateUser.init();
        updateUser.doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getParameter("email");

        final User user = this.userDao.getUserByID(userId);
        this.userDao.deleteUserByID(userId);

        assertThat(user.getLogin(), is("user"));
        assertThat(user.getPassword(), is("user"));
        assertThat(user.getEmail(), is("user@user.ru"));
    }
}