package ru.job4j.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ru.job4j.domain.User;

/**
 * User repository.
 *
 * @author Alexey Voronin.
 * @since 09.01.2018.
 */
@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    /**
     * Return user by email.
     *
     * @param email email.
     * @return user.
     */
    User findByEmail(final String email);
}
