package ru.job4j.dao;

import ru.job4j.model.User;

import java.util.List;

/**
 * User dao.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public interface UserDao {

    /**
     * Add user.
     *
     * @param user user.
     * @return id of the added entry.
     */
    int addUser(final User user);

    /**
     * Return user from database by id.
     *
     * @param id id.
     * @return user.
     */
    User getUserByID(final int id);

    /**
     * Update user to database.
     *
     * @param id         user ID for the update.
     * @param updateUser updated user.
     * @return number of update records.
     */
    int updateUser(final int id, final User updateUser);

    /**
     * Delete user from database.
     *
     * @param id user id for the delete.
     * @return number of deleted records.
     */
    int deleteUserByID(final int id);

    /**
     * Get all users from database.
     *
     * @return list users.
     */
    List<User> getAllUsers();
}
