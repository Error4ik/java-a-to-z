package ru.job4j.comand;

import org.hibernate.Session;

/**
 * CRUD interface.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.11.2017.
 */
public interface CRUDOperation<T> {

    /**
     * Database operation.
     *
     * @param session hibernate session.
     * @param value   entity.
     */
    void execute(Session session, T value);
}
