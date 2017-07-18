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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

/**
 * Delete user controller.
 *
 * @author Alexey Voronin.
 * @since 17.07.2017.
 */
public class DeleteUserTest {

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
    public void deleteUser() throws ServletException, IOException {
        final int userId = this.userDao.addUser(new User(0, "test", "test", "test", new Date(), new Role(1, "admin")));
        final DeleteUser deleteUser = new DeleteUser();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getParameter("id")).thenReturn(String.format("%s", userId));
        when(request.getSession()).thenReturn(session);
        when(session.getAttribute("adminID")).thenReturn(String.format("%s", userId));

        deleteUser.init();
        deleteUser.doPost(request, response);

        verify(request, atLeast(1)).getParameter("id");
        verify(session, atLeast(1)).getAttribute("adminID");

        final User user = this.userDao.getUserByID(userId);

        assertNull(user);
    }
}