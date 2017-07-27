package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.model.City;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * Implement CityDao for the database.
 *
 * @author Alexey Voronin.
 * @since 23.07.2017.
 */
public class CityToDB implements CityDao {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(CityToDB.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param source source.
     */
    public CityToDB(final DataSource source) {
        this.source = source;
        this.init();
    }

    @Override
    public int addCity(final City city) {
        int generateKey = 0;
        final String query = "INSERT INTO city (city, country_id) VALUES (?, ?) RETURNING id";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, city.getCity());
            statement.setInt(2, city.getCountryID());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    generateKey = resultSet.getInt("id");
                }
            }
            LOG.info(String.format("Added to dataBase: %s", city));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKey;
    }

    @Override
    public City getCityById(final int id) {
        City city = null;
        final String query = "SELECT * FROM city WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    city = new City(resultSet.getInt("id"), resultSet.getString("city"), resultSet.getInt("country_id"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return city;
    }

    @Override
    public int removeCityById(int id) {
        int result = 0;
        final String query = "DELETE FROM city WHERE id = ?";
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
    public List<City> getAllCity() {
        List<City> list = new ArrayList<>();
        final String query = "SELECT * FROM city";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new City(resultSet.getInt("id"), resultSet.getString("city"), resultSet.getInt("country_id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public List<City> getAllCityByCountry(final int id) {
        List<City> list = new ArrayList<>();
        final String query = "SELECT * FROM city WHERE country_id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(new City(resultSet.getInt("id"), resultSet.getString("city"), resultSet.getInt("country_id")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public City getCityByName(final String name) {
        City city = null;
        final String query = "SELECT * FROM city WHERE city LIKE ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, String.format("%s%s", name, "%"));
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    city = new City(resultSet.getInt("id"), resultSet.getString("city"), resultSet.getInt("country_id"));
                }
            }
            LOG.info(String.format("City returned: %s ", city));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return city;
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
