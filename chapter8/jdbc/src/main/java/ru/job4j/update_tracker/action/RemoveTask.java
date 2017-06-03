package ru.job4j.update_tracker.action;

import org.apache.log4j.Logger;
import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query Remove task.
 */
public class RemoveTask extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RemoveTask.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public RemoveTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the ID for the task you want to delete: ").trim());
        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("BEGIN; DELETE FROM comments WHERE task_id = ?;"
                     + " DELETE FROM task WHERE id = ?; COMMIT")) {
            ps.setInt(1, id);
            ps.setInt(2, id);
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}