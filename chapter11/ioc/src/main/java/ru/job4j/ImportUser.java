package ru.job4j;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.job4j.models.User;
import ru.job4j.storage.JdbcStorage;
import ru.job4j.storage.Storage;

/**
 * Run for jdbc template.
 *
 * @author Alexey Voronin.
 * @since 08.12.2017.
 */
public class ImportUser {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring-context.xml");
        Storage<User> storage = context.getBean(JdbcStorage.class);
        User user = new User();
        user.setName("test");
        user.setLogin("test@mail.ru");
        user.setPassword("password");
        storage.save(user);
    }
}
