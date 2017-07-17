package ru.job4j.dao;

import ru.job4j.model.Role;

import java.util.List;

/**
 * Role dao interface.
 *
 * @author Alexey Voronin.
 * @since 16.07.2017.
 */
public interface RoleDao {

    /**
     * Add new role.
     *
     * @param role role.
     * @return id.
     */
    int addRole(final Role role);

    /**
     * Remove role.
     *
     * @param id the roles to be removed.
     * @return removed rows.
     */
    int removeRoleById(final int id);

    /**
     * Get role by id.
     *
     * @param id the roles.
     * @return role.
     */
    Role getRoleById(final int id);

    /**
     * Get all roles.
     *
     * @return list roles.
     */
    List<Role> getAllRole();
}
