package ru.job4j.util;

import lombok.extern.log4j.Log4j;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Util.
 *
 * @author Alexey Voronin.
 * @since 31.10.2017.
 */
@Log4j
public class HibernateUtil {

    /**
     * Session factory.
     */
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            throw new ExceptionInInitializerError("SessionFactory creation failed");
        }
    }

    /**
     *
     * @return SessionFactory.
     */
    public static SessionFactory getSessionFactory() {
        return SESSION_FACTORY;
    }
}
