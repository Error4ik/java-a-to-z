package ru.job4j.store;

/**
 * User IStore.
 *
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class UserStore extends BaseStore<User> {

    /**
     * Constructor.
     * @param size size to storage.
     */
    public UserStore(int size) {
        super(size);
    }
}
