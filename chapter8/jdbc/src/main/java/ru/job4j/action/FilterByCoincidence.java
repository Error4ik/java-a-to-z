package ru.job4j.update_tracker.action;

import org.apache.log4j.Logger;
import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;
import ru.job4j.update_tracker.view.ConsoleView;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.ResultSet;

/**
 * Query Filter by coincidence.
 *
 * @author Alexey Voronin.
 * @since 02.06.2017.
 */
public class FilterByCoincidence extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(FilterByCoincidence.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public FilterByCoincidence(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        StringBuilder sb = new StringBuilder();
        final String filter = inputData.getInput("Enter filter: ").trim();
        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("SELECT * FROM task WHERE name LIKE ?")) {
            ps.setString(1, String.format("%s%s%s", "%", filter, "%"));
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
