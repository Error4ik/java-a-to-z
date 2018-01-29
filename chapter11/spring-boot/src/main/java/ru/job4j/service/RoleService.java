package ru.job4j.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.job4j.domain.Role;
import ru.job4j.repository.RoleRepository;

/**
 * Role service.
 *
 * @author Alexey Voronin.
 * @since 27.01.2018.
 */
@Service
public class RoleService {

    /**
     * Role repository.
     */
    @Autowired
    RoleRepository roleRepository;

    /**
     * Get role by id.
     *
     * @param id id.
     * @return role.
     */
    public Role getRoleById(final int id) {
        return this.roleRepository.findById(id).get();
    }
}
