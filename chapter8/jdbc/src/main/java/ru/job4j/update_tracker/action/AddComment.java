package ru.job4j.update_tracker.action;

import org.apache.log4j.Logger;
import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;

import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.DriverManager;
import java.sql.Timestamp;

/**
 * Query Adds comment to task.
 */
public class AddComment extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(AddComment.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public AddComment(final String id, final String name) {
        super(id, name);
    }


    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        int id = Integer.parseInt(inputData.getInput("Enter the task ID to add a comment: "));
        String comment = inputData.getInput("Enter commentary: ").trim();
        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("INSERT INTO comments (task_id, create_date, comment) "
                     + "VALUES (?, ?, ?)")) {
            ps.setInt(1, id);
            ps.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            ps.setString(3, comment);
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
