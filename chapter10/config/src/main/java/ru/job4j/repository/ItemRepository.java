package ru.job4j.repository;

import lombok.NonNull;
import lombok.extern.log4j.Log4j;
import org.hibernate.Session;
import ru.job4j.comand.AllEntity;
import ru.job4j.comand.CRUDOperation;
import ru.job4j.comand.EntityById;
import ru.job4j.models.Item;

import java.util.List;

/**
 * Item repository.
 *
 * @author Alexey Voronin.
 * @since 15.11.2017.
 */
@Log4j
public class ItemRepository extends CommonRepository<Item> {

    /**
     * Add item to database.
     *
     * @param item item.
     */
    public void addItem(@NonNull final Item item) {
        super.execute(new CRUDOperation<Item>() {
            @Override
            public void execute(Session session, Item object) {
                session.saveOrUpdate(object);
            }
        }, item);
    }

    /**
     * Return item by id.
     *
     * @param id id.
     * @return item.
     */
    public Item getItemById(@NonNull final int id) {
        return super.getEntityById(new EntityById<Item>() {
            @Override
            public Item getById(Session session) {
                return session.get(Item.class, id);
            }
        });
    }

    /**
     * Return all items.
     *
     * @return list.
     */
    public List<Item> getAllItems() {
        return super.getAllEntity(new AllEntity<Item>() {
            @Override
            public List<Item> getAll(Session session) {
                return session.createQuery("from Item order by id asc").list();
            }
        });
    }
}
