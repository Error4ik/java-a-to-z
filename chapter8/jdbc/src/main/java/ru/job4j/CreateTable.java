package ru.job4j;

import org.apache.log4j.Logger;
import ru.job4j.dao.Tracker;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create and drop table.
 *
 * @author Alexey Voronin.
 * @since 12.06.2017.
 */
public class CreateTable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Tracker.class);

    /**
     * DataSource.
     */
    private final DataSource source;


    /**
     * Constructor.
     *
     * @param source dataSource.
     */
    public CreateTable(final DataSource source) {
        this.source = source;
    }


    /**
     * Create table from file.
     */
    public void createTable() {
        try (InputStream is = CreateTable.class.getClass().getResourceAsStream("/createTables.sql");
             Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(ReadFile.readFile(is))) {
            statement.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
