package ru.job4j.user_storage;

import org.apache.log4j.Logger;

import java.util.Map;
import java.util.HashMap;
import java.util.UUID;
import java.util.List;
import java.util.ArrayList;


/**
 * User storage.
 *
 * @author Alexey Voronin.
 * @since 25.04.2017.
 */
public class UserStorage {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(UserStorage.class);

    /**
     * Stores users.
     */
    private Map<UUID, User> map = new HashMap<>();

    /**
     * Add new user.
     *
     * @param user user.
     */
    public synchronized void addUser(final User user) {
        this.map.put(user.getUuid(), user);
    }

    /**
     * Remove user.
     *
     * @param user user.
     */
    public synchronized void removeUser(final User user) {
        this.map.remove(user.getUuid());
    }

    /**
     * Add money to user account.
     *
     * @param user  user.
     * @param money amount of money.
     */
    public synchronized void addMoney(final User user, final double money) {
        if (money > 0) {
            this.map.get(user.getUuid()).setBalance(money);
        }
    }

    /**
     * @return List of users.
     */
    public synchronized List getUsers() {
        List<User> list = new ArrayList<>();
        for (User user : map.values()) {
            list.add(user);
        }
        return list;
    }

    /**
     * Print all users to the console.
     */
    public synchronized void printAllUsers() {
        for (User user : map.values()) {
            System.out.println(user);
        }
    }

    /**
     * Transfer money from one account to another.
     *
     * @param from   one account.
     * @param amount count of money.
     * @param to     to another account.
     */
    public synchronized void transaction(final User from, final double amount, final User to) {
        //LOGGER.info(String.format("%s [send] %s [amount %s]", from, to, amount));
        if (from.getBalance() >= amount) {
            from.setBalance(from.getBalance() - amount);
            to.setBalance(to.getBalance() + amount);
            //LOGGER.info(String.format("%s%s%s", from, System.getProperty("line.separator"), to));
        } else {
            LOGGER.info(String.format("Not enough money on the account: %s", from));
        }
    }
}
