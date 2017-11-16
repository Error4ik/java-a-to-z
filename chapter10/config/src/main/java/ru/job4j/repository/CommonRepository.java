package ru.job4j.repository;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.job4j.comand.AllEntity;
import ru.job4j.comand.CRUDOperation;
import ru.job4j.comand.EntityById;
import ru.job4j.util.HibernateUtil;

import java.util.List;

/**
 * Common repository
 *
 * @param <T> any type.
 * @author Alexey Voronin.
 * @since 15.11.2017.
 */
@Log4j
public abstract class CommonRepository<T> {

    /**
     * CRUD operation database.
     *
     * @param command crud interface.
     * @param value   anu object.
     */
    public void execute(@NonNull final CRUDOperation<T> command, @NonNull final T value) {
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            command.execute(session, value);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    /**
     * Return entity by id.
     *
     * @param entityById id.
     * @return entity.
     */
    public T getEntityById(@NonNull final EntityById<T> entityById) {
        Session session = null;
        T value = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            value = entityById.getById(session);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return value;
    }

    /**
     * Return list entity.
     *
     * @param entity entity interface.
     * @return list.
     */
    public List<T> getAllEntity(AllEntity<T> entity) {
        List<T> list = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = entity.getAll(session);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            log.error(e.getMessage(), e);
            if (session != null) {
                session.getTransaction().rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return list;
    }
}
