package ru.job4j.dao;

import org.apache.log4j.Logger;
import ru.job4j.models.Comment;
import ru.job4j.models.Task;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Timestamp;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracker.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class Tracker implements ITracker {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Tracker.class);

    /**
     * DataSource.
     */
    private final DataSource source;

    /**
     * Constructor.
     *
     * @param source dataSource.
     */
    public Tracker(final DataSource source) {
        this.source = source;
    }

    @Override
    public int addTask(final Task task) {
        String sql = "INSERT INTO task(name, description, create_date) VALUES (?, ?, ?)";
        int generateKeys = 0;
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setTimestamp(3, new Timestamp(task.getCreateDate().getTime()));
            generateKeys = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Task addedTask = new Task(generateKeys, task.getName(),
                task.getDescription(), task.getCreateDate().getTime());
        LOGGER.info(String.format("%s added to task table.", addedTask));
        return generateKeys;
    }

    @Override
    public int addComment(final Comment comment) {
        String sql = "INSERT INTO comments (task_id, create_date, comment) VALUES (?, ?, ?)";
        int generateKeys = 0;
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, comment.getTaskID());
            statement.setTimestamp(2, new Timestamp(comment.getCreateDate().getTime()));
            statement.setString(3, comment.getComment());
            generateKeys = statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        Comment addedComment = new Comment(generateKeys, comment.getTaskID(),
                comment.getCreateDate().getTime(), comment.getComment());
        LOGGER.info(String.format("%s added to comments table.", addedComment));
        return generateKeys;
    }

    @Override
    public Task getTaskById(final int taskID) {
        String sql = "SELECT * FROM task WHERE id = ?";
        Task task = null;
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, taskID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    task = new Task(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("%s returned from the task table.", task));
        return task;
    }

    @Override
    public Comment getCommentById(final int commentID) {
        String sql = "SELECT * FROM comments WHERE id = ?";
        Comment comment = null;
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, commentID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    comment = new Comment(rs.getInt("id"), rs.getInt("task_id"),
                            rs.getTimestamp("create_date").getTime(), rs.getString("comment"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("%s returned from the task comments.", comment));
        return comment;
    }

    @Override
    public List<Task> getAllTask() {
        String sql = "SELECT * FROM task";
        List<Task> tasks = new ArrayList<>();
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tasks;
    }

    @Override
    public List<Comment> getAllCommentToTask(final int taskID) {
        String sql = "SELECT * FROM comments WHERE task_id = ?";
        List<Comment> comments = new ArrayList<>();
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, taskID);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(rs.getInt("id"), rs.getInt("task_id"),
                            rs.getTimestamp("create_date").getTime(), rs.getString("comment"));
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return comments;
    }

    @Override
    public boolean removeTaskById(final int taskID) {
        String sql = "BEGIN; DELETE FROM comments WHERE task_id = ?; DELETE FROM task WHERE id = ?; COMMIT";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, taskID);
            statement.setInt(2, taskID);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("Task with ID %s was removed from the table.", taskID));
        return true;
    }

    @Override
    public boolean removeComment(final int commentID) {
        String sql = "DELETE FROM comments WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setInt(1, commentID);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("Comment with ID %s was removed from the table.", commentID));
        return true;
    }

    @Override
    public boolean updateTask(final Task task) {
        String sql = "UPDATE task SET name = ?, description = ? WHERE id = ?";
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, task.getName());
            statement.setString(2, task.getDescription());
            statement.setInt(3, task.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        LOGGER.info(String.format("%s updated.", task));
        return true;
    }

    @Override
    public List<Task> getTaskByName(String name) {
        String sql = "SELECT * FROM task WHERE name = ?";
        List<Task> tasks = new ArrayList<>();
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, name);
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("create_date").getTime()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tasks;
    }

    @Override
    public List<Task> getTaskByCoincidence(final String coincidence) {
        String sql = "SELECT * FROM task WHERE name LIKE ?";
        List<Task> tasks = new ArrayList<>();
        try (Connection con = source.getConnection();
             PreparedStatement statement = con.prepareStatement(sql)) {
            statement.setString(1, String.format("%s%s%s", "%", coincidence, "%"));
            try (ResultSet rs = statement.executeQuery()) {
                while (rs.next()) {
                    tasks.add(new Task(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                            rs.getTimestamp("create_date").getTime()));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
        return tasks;
    }
}
