package ru.job4j.update_tracker.action;

import org.apache.log4j.Logger;
import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query Remove comment to task.
 */
public class RemoveComment extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(RemoveComment.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public RemoveComment(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        int id = Integer.parseInt(
                inputData.getInput("Enter the ID you want to delete a comment: ").trim());
        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("DELETE FROM comments WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
