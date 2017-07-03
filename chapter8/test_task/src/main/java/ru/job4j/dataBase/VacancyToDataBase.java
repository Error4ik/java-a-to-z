package ru.job4j.dataBase;

import org.apache.log4j.Logger;
import ru.job4j.model.Vacancy;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Implement IVacancy, added vacancy to DataBase.
 *
 * @author Alexey Voronin.
 * @since 28.06.2017.
 */
public class VacancyToDataBase implements IVacancy {

    /**
     * Logger.
     */
    private static final Logger LOG = Logger.getLogger(VacancyToDataBase.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param dataSource data source
     */
    public VacancyToDataBase(final DataSource dataSource) {
        this.source = dataSource;
    }

    @Override
    public int addVacancy(final Vacancy vacancy) {
        int generateKeys = 0;
        final String query = "insert into job_offers (title, body, create_date) VALUES (?, ?, ?) RETURNING id";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setString(1, vacancy.getTitle());
            statement.setString(2, vacancy.getBody());
            statement.setTimestamp(3, new Timestamp(vacancy.getCreateDate().getTime()));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    generateKeys = rs.getInt("id");
                }
            }
            LOG.info(String.format("Added a vacancy to the database %s %s %s",
                    generateKeys, vacancy.getTitle(), vacancy.getCreateDate()));
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return generateKeys;
    }

    @Override
    public Vacancy getVacancyByID(final int id) {
        Vacancy vacancy = null;
        final String query = "select * from job_offers where id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    vacancy = new Vacancy(
                            rs.getString("title"), rs.getString("body"), rs.getTimestamp("create_date"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return vacancy;
    }

    @Override
    public int removeVacancyByID(final int id) {
        int result = 0;
        final String query = "delete from job_offers where id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            statement.setInt(1, id);
            result = statement.executeUpdate();
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        LOG.info(String.format("Rows deleted; %s", result));
        return result;
    }

    @Override
    public List<Vacancy> getAllVacancy() {
        List<Vacancy> list = new ArrayList<>();
        final String query = "select * from job_offers";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    list.add(new Vacancy(
                            rs.getString("title"), rs.getString("body"), rs.getTimestamp("create_date")));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return list;
    }

    @Override
    public long getTheLatestVacancyDate() {
        long milliseconds = 0;
        final String query = "select max(create_date) as date from job_offers";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    milliseconds = new Date(rs.getTimestamp("date").getTime()).getTime();
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return milliseconds;
    }

    @Override
    public int getNumberOfRows() {
        String query = "select count(*) as number from job_offers";
        int result = 0;
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(query)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    result = Integer.parseInt(rs.getString("number"));
                }
            }
        } catch (SQLException e) {
            LOG.error(e.getMessage(), e);
        }
        return result;
    }
}
