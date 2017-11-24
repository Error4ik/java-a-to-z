package ru.job4j.repository;

import org.junit.Test;
import ru.job4j.models.User;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * User repository test.
 *
 * @author Alexey Voronin.
 * @since 24.11.2017.
 */
public class UserRepositoryTest {

    private final UserRepository repository = new UserRepository();

    @Test
    public void getUserByEmailAndPassword() {
        final String email = "alex@mail.ru";
        final String password = "555";
        final User user = new User();
        user.setEmail(email);
        user.setPassword(password);
        user.setId(repository.save(user));
        assertThat(user.getEmail(), is(repository.getUserByEmailAndPass(email, password).get(0).getEmail()));
    }
}
