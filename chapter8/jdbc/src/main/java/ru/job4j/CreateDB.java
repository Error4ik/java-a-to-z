package ru.job4j.update_tracker;

import org.apache.log4j.Logger;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Create DB.
 *
 * @author Alexey Voronin.
 * @since 01.06.2017.
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
        InputStream is = CreateDB.class.getClass().getResourceAsStream("/create_db.sql");

        Connection con = null;
        PreparedStatement ps = null;
        try {
            con = DriverManager.getConnection(this.url, this.userName, this.password);
            ps = con.prepareStatement(ReadFile.readFile(is));
            ps.execute();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}

