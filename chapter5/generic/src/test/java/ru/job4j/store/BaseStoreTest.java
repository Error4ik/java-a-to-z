package ru.job4j.store;

import org.junit.Test;

import java.util.NoSuchElementException;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertThat;
import static org.hamcrest.core.Is.is;

/**
 * BaseStore test.
 *
 * @author Alexey Voronin.
 * @since 12.03.2017.
 */
public class BaseStoreTest {

    /**
     * Method add.
     * If you abandon the way to add, then the element is added to the array.
     */
    @Test
    public void whenAddObjectToArrayThenTheGetMethodReturnTheSameObject() {
        final User user = new User("1");
        final BaseStore store = new UserStore(1);

        store.add(user);

        assertThat(store.getSimpleArray().get(0), is(user));
    }

    /**
     * Method add.
     * if array is full, and add new item, then array the increased.
     */
    @Test
    public void whenArrayIsFullAndAddNewItemShouldArrayTheIncreased() {
        final User user = new User("1");
        final BaseStore store = new BaseStore(2);
        final int expectedValue = 4;

        store.add(user);
        store.add(user);
        store.add(user);

        assertThat(store.getSimpleArray().getSize(), is(expectedValue));
    }

    /**
     * Method delete.
     * If item is deleted then method get return null.
     */
    @Test
    public void whenDeleteItemFromArrayShouldInArrayNotContainedThisItem() {
        final Role role1 = new Role("1");
        final Role role2 = new Role("2");
        final BaseStore store = new RoleStore(2);

        store.add(role1);
        store.add(role2);
        store.delete(role1);

        assertNull(store.getSimpleArray().get(0));
    }

    /**
     * Method update.
     * If you add item to the array in place of an existing item, the new item will replace the old one.
     */
    @Test
    public void whenAddItemToTheArrayInPlaceOfAnExistingItemThenTheNewItemWilReplaceTheOldOne() {
        final User user1 = new User("user1");
        final User user2 = new User("user2");
        final Role role = new Role("role1");
        final BaseStore baseStore = new BaseStore(2);
        final Role expectedValue = role;

        baseStore.add(user1);
        baseStore.add(user2);
        baseStore.update(user1, role);

        assertThat(baseStore.getSimpleArray().get(0), is(expectedValue));
    }

    /**
     * Method setId.
     */
    @Test
    public void whenSetIdThenReturnValidValue() {
        final Role role = new Role("5");
        final String expectedValue = "Bob";

        role.setId("Bob");

        assertThat(role.getId(), is(expectedValue));
    }

    /**
     * Method get.
     * Return object by id.
     */
    @Test
    public void whenGetWhenReturnValidObject() {
        final User user1 = new User("user1");
        final User user2 = new User("user2");
        final BaseStore baseStore = new BaseStore(2);

        baseStore.add(user1);
        baseStore.add(user2);

        assertThat(baseStore.get("user2"), is(user2));
    }

    /**
     * Method get.
     * If item equals null, throws NoSuchElementException.
     */
    @Test(expected = NoSuchElementException.class)
    public void whenListIfEmptyGetShouldThrowsException() {
        final BaseStore baseStore = new BaseStore(2);

        baseStore.get("1");
    }
}