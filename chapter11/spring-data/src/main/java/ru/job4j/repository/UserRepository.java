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
     * Return user by name.
     *
     * @param name name.
     * @return user.
     */
    User findByName(final String name);
}
