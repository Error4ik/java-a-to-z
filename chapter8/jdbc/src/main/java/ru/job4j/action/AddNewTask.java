package ru.job4j.action;

import org.apache.log4j.Logger;
import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Query Adds new task to DB.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class AddNewTask extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AddNewTask.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public AddNewTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        String name = inputData.getInput("Enter the name new task: ").trim();
        String description = inputData.getInput("Enter the description new task: ").trim();
        if (!name.equals("")) {
            try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
                 PreparedStatement ps = con.prepareStatement("INSERT INTO task(name, description, create_date) VALUES (?, ?, ?)")) {
                ps.setString(1, name);
                ps.setString(2, description);
                ps.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
                ps.executeQuery();
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }
}
