package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.model.Country;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement CountryDao for the database.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public class CountryToDB implements CountryDao {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(CountryToDB.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param source source.
     */
    public CountryToDB(final DataSource source) {
        this.source = source;
        this.init();
    }

    @Override
    public int addCountry(final Country country) {
        int generateKey = 0;
        final String query = "INSERT INTO country (country) VALUES (?) RETURNING id";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, country.getCountry());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    generateKey = resultSet.getInt("id");
                }
            }
            LOG.info(String.format("Added to dataBase: %s", country));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKey;
    }

    @Override
    public Country getCountryById(final int id) {
        Country country = null;
        final String query = "SELECT * FROM country WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    country = new Country(resultSet.getInt("id"), resultSet.getString("country"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return country;
    }

    @Override
    public void removeCountryById(final int id) {
        final String query = "BEGIN; DELETE FROM city WHERE country_id = ?; DELETE FROM country WHERE id = ?; COMMIT";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            statement.setInt(2, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
    }

    @Override
    public List<Country> getAllCountry() {
        List<Country> list = new ArrayList<>();
        final String query = "SELECT * FROM country";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new Country(resultSet.getInt("id"), resultSet.getString("country")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public Country getCountryByName(final String name) {
        Country country = null;
        final String query = "SELECT * FROM country WHERE country = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, name);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    country = new Country(resultSet.getInt("id"), resultSet.getString("country"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return country;
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
