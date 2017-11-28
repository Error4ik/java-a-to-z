package ru.job4j.repository;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import ru.job4j.commands.AllEntity;
import ru.job4j.commands.CRUDOperation;
import ru.job4j.commands.EntityByName;
import ru.job4j.commands.EntityById;
import ru.job4j.util.HibernateUtil;

import java.util.List;

/**
 * Common repository.
 *
 * @author Alexey Voronin.
 * @since 16.11.2017.
 */
@Log4j
public abstract class CommonRepository<T> {

    public int execute(@NonNull final CRUDOperation<T> command, @NonNull final T value) {
        Session session = null;
        int id = 0;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            id = command.execute(session, value);
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
        return id;
    }

    public T getEntityById(@NonNull final EntityById<T> entityById) {
        Session session = null;
        T value = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            value = entityById.getEntityById(session);
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

    public T getEntityByName(@NonNull final EntityByName<T> entityByName) {
        Session session = null;
        T value = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            value = entityByName.getEntityByName(session);
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

    public List<T> getAllEntity(@NonNull final AllEntity<T> allEntity) {
        Session session = null;
        List<T> list = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            session.beginTransaction();
            list = allEntity.getAll(session);
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
