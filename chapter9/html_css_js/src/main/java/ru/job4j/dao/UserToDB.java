package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.model.City;
import ru.job4j.model.Country;
import ru.job4j.model.Role;
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
        final String query = String.format("INSERT INTO users %s %s",
                "(login, password, email, create_date, role_id, country_id, city_id)",
                "VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id");
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, user.getLogin());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getEmail());
            statement.setTimestamp(4, new Timestamp(user.getCreateDate().getTime()));
            statement.setInt(5, user.getRole().getId());
            statement.setInt(6, user.getCountry().getId());
            statement.setInt(7, user.getCity().getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    generateKey = resultSet.getInt("id");
                }
            }
            LOG.info(String.format("Added to dataBase: %s", new User(generateKey, user.getLogin(),
                    user.getPassword(), user.getEmail(), user.getCreateDate(), user.getRole(),
                    user.getCountry(), user.getCity())));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKey;
    }

    @Override
    public User getUserByID(final int id) {
        User user = null;
        final String query = String.format("SELECT u.id, u.login, u.password, u.email, u.create_date, u.role_id ,%s %s %s %s",
                "role.role, u.country_id, country.country, u.city_id, city.city FROM users AS u",
                "INNER JOIN role ON role.id = u.role_id",
                "INNER JOIN country on country.id = u.country_id",
                "INNER JOIN city on city.id = u.city_id WHERE u.id = ?");
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"),
                            resultSet.getString("email"), new Date(resultSet.getTimestamp("create_date").getTime()),
                            new Role(Integer.parseInt(resultSet.getString("role_id")), resultSet.getString("role")),
                            new Country(resultSet.getInt("country_id"), resultSet.getString("country")),
                            new City(resultSet.getInt("city_id"), resultSet.getString("city"), resultSet.getInt("country_id")));
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
        final String query = "UPDATE users SET login = ?, password = ?, email = ?, role_id = ?, country_id = ?, city_id = ? WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, updateUser.getLogin());
            statement.setString(2, updateUser.getPassword());
            statement.setString(3, updateUser.getEmail());
            statement.setInt(4, updateUser.getRole().getId());
            statement.setInt(5, updateUser.getCountry().getId());
            statement.setInt(6, updateUser.getCity().getId());
            statement.setInt(7, id);
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
        final String query = String.format("SELECT u.id, u.login, u.password, u.email, u.create_date, u.role_id ,%s %s %s %s",
                "role.role, u.country_id, country.country, u.city_id, city.city FROM users AS u",
                "INNER JOIN role ON role.id = u.role_id",
                "INNER JOIN country on country.id = u.country_id",
                "INNER JOIN city on city.id = u.city_id ORDER BY u.id ASC");
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"),
                            resultSet.getString("email"), new Date(resultSet.getTimestamp("create_date").getTime()),
                            new Role(Integer.parseInt(resultSet.getString("role_id")), resultSet.getString("role")),
                            new Country(resultSet.getInt("country_id"), resultSet.getString("country")),
                            new City(resultSet.getInt("city_id"), resultSet.getString("city"), resultSet.getInt("country_id"))));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public User getUserByLoginAndPassword(final String login, final String password) {
        User user = null;
        final String query = String.format("SELECT u.id, u.login, u.password, u.email, u.create_date, u.role_id ,%s %s %s %s",
                "role.role, u.country_id, country.country, u.city_id, city.city FROM users AS u",
                "INNER JOIN role ON role.id = u.role_id",
                "INNER JOIN country on country.id = u.country_id",
                "INNER JOIN city on city.id = u.city_id WHERE u.login = ? AND u.password = ? ");
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, login);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    user = new User(resultSet.getInt("id"), resultSet.getString("login"), resultSet.getString("password"),
                            resultSet.getString("email"), resultSet.getTimestamp("create_date"),
                            new Role(Integer.parseInt(resultSet.getString("role_id")), resultSet.getString("role")),
                            new Country(resultSet.getInt("country_id"), resultSet.getString("country")),
                            new City(resultSet.getInt("city_id"), resultSet.getString("city"), resultSet.getInt("country_id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return user;
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
