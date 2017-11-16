package ru.job4j.commands;

import org.hibernate.Session;

/**
 * Interface CRUD operation.
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
public interface CRUDOperation<T> {

    /**
     * Database operation.
     *
     * @param session hibernate session.
     * @param value   entity.
     */
    void execute(final Session session, final T value);
}
