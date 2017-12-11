package ru.job4j.storage;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;

import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

/**
 * Test User storage class.
 *
 * @author Alexey Voronin.
 * @since 04.12.2017.
 */
public class UserStorageTest {

    @Test
    public void whenCalledSaveTheObjectShouldIsPlacedInTheStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        User user = new User();
        user.setId(1);
        user.setName("test");
        user.setLogin("test@mail.ru");
        user.setPassword("555");
        storage.save(user);
        assertThat(storage.getById(user.getId()), is(user));
    }

    @Test
    public void whenCalledUpdateTheObjectShouldIsUpdateInTheStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        User user = new User();
        user.setId(1);
        user.setName("alex");
        user.setLogin("alex@mail.ru");
        user.setPassword("555");
        storage.save(user);
        user.setPassword("333");
        user.setName("garry");
        user.setLogin("garry@mail.ru");
        storage.updateEntity(user);
        assertThat(storage.getById(1), is(user));
    }

    @Test
    public void whenCalledDeleteTheObjectShouldIsDeletedFromTheStorage() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        final int expectedValue = 1;
        User user = new User();
        user.setId(1);
        user.setName("Garry");
        user.setLogin("test@mail.ru");
        user.setPassword("555");
        storage.save(user);
        assertThat(storage.deleteEntity(user), is(expectedValue));
    }

    @Test
    public void whenCalledDeleteIfObjectNotInStorageShouldReturnMinusOne() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        final int expectedValue = -1;
        User user = new User();
        user.setId(99);
        assertThat(storage.deleteEntity(user), is(expectedValue));
    }

    @Test
    public void whenCalledGetAllEntityShouldReturnListEntities() {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(UserStorage.class);
        User user = new User();
        user.setId(1);
        user.setName("test1");
        User user2 = new User();
        user2.setId(2);
        user2.setName("test2");
        storage.save(user);
        storage.save(user2);
        List<User> actualList = storage.getAllEntity();
        assertTrue(actualList.contains(user));
        assertTrue(actualList.contains(user2));
    }
}