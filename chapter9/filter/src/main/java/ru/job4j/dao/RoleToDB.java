package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.model.Role;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement RoleDao for the database.
 *
 * @author Alexey Voronin.
 * @since 16.07.2017.
 */
public class RoleToDB implements RoleDao {

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
    public RoleToDB(final DataSource source) {
        this.source = source;
        this.init();
    }

    @Override
    public int addRole(final Role role) {
        int generateKey = 0;
        final String query = "INSERT INTO role (role) VALUES (?) RETURNING id";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, role.getRole());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    generateKey = resultSet.getInt("id");
                }
            }
            LOG.info(String.format("Added to dataBase: %s", new Role(generateKey, role.getRole())));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKey;
    }

    @Override
    public int removeRoleById(final int id) {
        int result = 0;
        final String query = "DELETE FROM role WHERE id = ?";
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
    public Role getRoleById(final int id) {
        Role role = null;
        final String query = "SELECT * FROM role WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    role = new Role(resultSet.getInt("id"), resultSet.getString("role"));
                }
            }
            LOG.info(String.format("Role returned: %s", role));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return role;
    }

    @Override
    public List<Role> getAllRole() {
        List<Role> roles = new ArrayList<>();
        final String query = "SELECT * FROM role";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    roles.add(new Role(resultSet.getInt("id"), resultSet.getString("role")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }

        return roles;
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
