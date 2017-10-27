package ru.job4j.services;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import ru.job4j.models.Item;
import ru.job4j.util.HibernateUtil;

import java.util.List;

/**
 * Processing to the database class.
 *
 * @author Alexey Voronin.
 * @since 25.10.2017.
 */
@Log4j
public class ItemService {

    /**
     * Save item to database.
     *
     * @param item item.
     */
    public void saveOrUpdate(@NonNull final Item item) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            session.saveOrUpdate(item);
            session.getTransaction().commit();
            session.close();
        }
        log.info(String.format("Save item %s", item));
    }

    /**
     * Return list items.
     *
     * @return list item from database.
     */
    public List<Item> getItems() {
        List<Item> items;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            items = session.createQuery("from Item order by id asc").list();
            session.getTransaction().commit();
            session.close();
        }
        return items;
    }

    /**
     * Return item by id.
     *
     * @param id item id.
     * @return item.
     */
    public Item getItemById(@NonNull final int id) {
        Item item;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            session.beginTransaction();
            item = session.get(Item.class, id);
            session.getTransaction().commit();
            session.close();
        }
        return item;
    }
}
