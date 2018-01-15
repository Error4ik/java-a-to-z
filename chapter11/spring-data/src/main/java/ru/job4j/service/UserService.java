package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

/**
 * User service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class UserService {

    /**
     * The users storage.
     */
    @Autowired
    private UserRepository userRepository;

    /**
     * Save user to storage.
     *
     * @param value user.
     * @return saved user.
     */
    public User save(final User value) {
        return this.userRepository.save(value);
    }

    /**
     * Get user by id from storage.
     *
     * @param id id.
     * @return user.
     */
    public User getById(final int id) {
        return this.userRepository.findById(id).get();
    }

    /**
     * Get user bu name from storage.
     *
     * @param name name.
     * @return user.
     */
    public User getByName(final String name) {
        return userRepository.findByName(name);
    }
}
