package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Role;
import ru.job4j.domain.User;
import ru.job4j.repository.UserRepository;

import java.util.HashSet;
import java.util.Set;

/**
 * User service.
 *
 * @author Alexey Voronin.
 * @since 20.12.2017.
 */
@Service
public class UserService {

    /**
     * To encrypt the password.
     */
    @Autowired
    private BCryptPasswordEncoder encoder;

    /**
     * Security service. Authorize and Authenticate user.
     */
    @Autowired
    private SecurityService securityService;

    /**
     * Role service.
     */
    @Autowired
    private RoleService roleService;

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
     * @param email email.
     * @return user.
     */
    public User getByEmail(final String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Reg new user.
     *
     * @param user user.
     */
    public void regUser(final User user) {
        String pass = user.getPassword();
        user.setPassword(encoder.encode(user.getPassword()));
        Set<Role> roles = new HashSet<>();
        roles.add(this.roleService.getRoleById(1));
        user.setRoleSet(roles);
        this.save(user);
        securityService.autoLogin(user.getEmail(), pass);
    }
}
