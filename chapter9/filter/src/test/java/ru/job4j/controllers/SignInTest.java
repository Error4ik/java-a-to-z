package ru.job4j.controllers;

import org.junit.Test;
import ru.job4j.dao.UserDao;
import ru.job4j.dao.UserToDB;
import ru.job4j.database.PoolDataSource;
import ru.job4j.model.Role;
import ru.job4j.model.User;
import ru.job4j.settings.Settings;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.atLeast;

/**
 * Sign In test.
 *
 * @author Alexey Voronin.
 * @since 18.07.2017.
 */
public class SignInTest {

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
    public void signInTestAdmin() throws ServletException, IOException {
        final SignIn signIn = new SignIn();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("admin");
        when(request.getParameter("password")).thenReturn("admin");

        signIn.init();
        signIn.doPost(request, response);

        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
    }

    /**
     * UpdateUser.
     *
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Test
    public void signInTestUser() throws ServletException, IOException {
        final SignIn signIn = new SignIn();
        final int userId = this.userDao.addUser(new User(0, "user", "user", "user@user.ru", new Date(), new Role(2, "user")));
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("user");
        when(request.getParameter("password")).thenReturn("user");

        signIn.init();
        signIn.doPost(request, response);

        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        this.userDao.deleteUserByID(userId);
    }

    /**
     * UpdateUser.
     *
     * @throws ServletException exception.
     * @throws IOException      exception.
     */
    @Test
    public void returnError() throws ServletException, IOException {
        final SignIn signIn = new SignIn();
        final HttpServletRequest request = mock(HttpServletRequest.class);
        final HttpServletResponse response = mock(HttpServletResponse.class);
        final HttpSession session = mock(HttpSession.class);
        final RequestDispatcher dispatcher = mock(RequestDispatcher.class);
        when(request.getSession()).thenReturn(session);
        when(request.getParameter("login")).thenReturn("test5");
        when(request.getParameter("password")).thenReturn("test5");
        when(request.getAttribute("error")).thenReturn("Credintals is not valid!");
        when(request.getRequestDispatcher("/WEB-INF/views/SignIn.jsp")).thenReturn(dispatcher);

        signIn.init();
        signIn.doPost(request, response);

        verify(request, atLeast(1)).getSession();
        verify(request, atLeast(1)).getParameter("login");
        verify(request, atLeast(1)).getParameter("password");
        verify(request, atLeast(1)).getRequestDispatcher("/WEB-INF/views/SignIn.jsp");

        assertThat(request.getAttribute("error"), is("Credintals is not valid!"));
    }
}