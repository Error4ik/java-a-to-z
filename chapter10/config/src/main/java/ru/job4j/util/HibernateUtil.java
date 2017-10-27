package ru.job4j.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 * Util class.
 *
 * @author Alexey Voronin.
 * @since 27.10.2017.
 */
public class HibernateUtil {

    /**
     * Session factory.
     */
    private static final SessionFactory SESSION_FACTORY;

    static {
        try {
            SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        } catch (Throwable e) {
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
