package ru.job4j.dataBase;

import org.apache.log4j.Logger;
import ru.job4j.ReadFile;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Created table.
 *
 * @author Alexey Voronin.
 * @since 01.07.2017.
 */
public class CreateTable {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CreateDB.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param dataSource dataSource.
     */
    public CreateTable(final DataSource dataSource) {
        this.source = dataSource;
    }

    /**
     * Create DB from file.
     */
    public void createTable() {
        try (InputStream is = CreateDB.class.getClass().getResourceAsStream("/create_table.sql");
             Connection con = source.getConnection();
             PreparedStatement ps = con.prepareStatement(ReadFile.readFile(is));) {
            ps.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
