package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.model.User;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Implement UserDao for the database.
 *
 * @author Alexey Voronin.
 * @since 06.07.2017.
 */
public class UserToDB implements UserDao {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(UserToDB.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param source data source.
     */
    public UserToDB(final DataSource source) {
        this.source = source;
        this.init();
    }

    @Override
    public int addUser(final User user) {
        int generateKey = 0;
        final String query = "INSERT INTO users (name, login, email, create_date) VALUES (?, ?, ?, ?) RETURNING id";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getName());
            statement.setString(2, user.getLogin());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    generateKey = resultSet.getInt("id");
                }
            }
            LOG.info(String.format("Added to dataBase: %s", user));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKey;
    }

    @Override
    public User getUserByID(final int id) {
        User user = null;
        final String query = "SELECT * FROM users WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("login"),
                            resultSet.getString("email"), new Date(resultSet.getTimestamp("create_date").getTime()));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
    }

    @Override
    public int updateUser(final int id, final User updateUser) {
        int result = 0;
        final String query = "UPDATE users SET name = ?, login = ?, email = ? WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, updateUser.getName());
            statement.setString(2, updateUser.getLogin());
            statement.setString(3, updateUser.getEmail());
            statement.setInt(4, id);
            result = statement.executeUpdate();
            LOG.info(String.format("Rows updated; %s", result));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public int deleteUserByID(final int id) {
        int result = 0;
        final String query = "DELETE FROM users WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
            LOG.info(String.format("Rows deleted; %s", result));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }

    @Override
    public List<User> getAllUsers() {
        List<User> list = new ArrayList<>();
        final String query = "SELECT * FROM users ORDER BY id ASC";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new User(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("login"),
                            resultSet.getString("email"), new Date(resultSet.getTimestamp("create_date").getTime())));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    /**
     * Load driver.
     */
    private void init() {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
