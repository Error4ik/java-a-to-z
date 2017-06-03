package ru.job4j.action;

import org.apache.log4j.Logger;
import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.view.ConsoleView;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;

/**
 * Query show all task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ShowAllTask extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(ShowAllTask.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public ShowAllTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        StringBuilder sb = new StringBuilder();
        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("SELECT * FROM task;")) {
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    sb.append(String.format("%d, %s, %s %s", rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date")))
                            .append(System.getProperty("line.separator"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        new ConsoleView().execute(sb.toString());
    }
}
