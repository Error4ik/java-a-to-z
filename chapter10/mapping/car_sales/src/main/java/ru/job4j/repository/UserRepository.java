package ru.job4j.repository;

import lombok.NonNull;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.models.User;

import java.util.List;

/**
 * User repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public class UserRepository extends CommonRepository<User> {

    /**
     * Save user to database.
     *
     * @param user user.
     * @return user id.
     */
    public int save(@NonNull final User user) {
        return super.execute(new CRUDOperation<User>() {
            @Override
            public int execute(Session session, User value) {
                return (int) session.save(value);
            }
        }, user);
    }

    /**
     * Returns user by email and password.
     *
     * @param email email.
     * @param pass  password.
     * @return user.
     */
    public List<User> getUserByEmailAndPass(@NonNull final String email, @NonNull final String pass) {
        return super.getAllEntity(new AllEntity<User>() {
            @Override
            public List<User> getAll(Session session) {
                return session.createQuery("from User where email=:email and password=:password")
                        .setParameter("email", email)
                        .setParameter("password", pass)
                        .list();
            }
        });
    }
}
