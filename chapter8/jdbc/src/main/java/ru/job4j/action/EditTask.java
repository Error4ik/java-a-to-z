package ru.job4j.action;

import org.apache.log4j.Logger;
import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * Query Edit task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class EditTask extends BaseAction {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(EditTask.class);

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public EditTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        int id = Integer.parseInt(inputData.getInput("Enter the task ID that you want to edit: ").trim());
        String name = inputData.getInput("Enter new task name: ").trim();
        String description = inputData.getInput("Enter new desc: ").trim();

        try (Connection con = DriverManager.getConnection(tracker.getUrl(), tracker.getUserName(), tracker.getPassword());
             PreparedStatement ps = con.prepareStatement("UPDATE task SET name = ?, description = ? WHERE id = ?")) {
            ps.setString(1, name);
            ps.setString(2, description);
            ps.setInt(3, id);
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }
}
