package ru.job4j.dataBase;

import org.apache.log4j.Logger;
import ru.job4j.ReadFile;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create DB.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class CreateDB {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(CreateDB.class);

    /**
     * Connect to DB url.
     */
    private final String url;

    /**
     * User name.
     */
    private final String userName;

    /**
     * Password.
     */
    private final String password;

    /**
     * Constructor.
     *
     * @param url      url.
     * @param userName name.
     * @param password password.
     */
    public CreateDB(final String url, final String userName, final String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Create DB from file.
     */
    public void createDB() {
        try (InputStream is = CreateDB.class.getClass().getResourceAsStream("/create_db.sql");
             Connection con = DriverManager.getConnection(this.url, this.userName, this.password);
             PreparedStatement ps = con.prepareStatement(ReadFile.readFile(is));) {
            ps.execute();
        } catch (SQLException | IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
