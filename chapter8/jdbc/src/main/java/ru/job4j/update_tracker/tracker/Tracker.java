package ru.job4j.update_tracker.tracker;

import org.apache.log4j.Logger;
import ru.job4j.update_tracker.ReadFile;
import ru.job4j.update_tracker.models.Comment;
import ru.job4j.update_tracker.models.Task;

import java.io.InputStream;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

/**
 * Tracker.
 */
public class Tracker {

    /**
     * Logger.
     */
    private static final Logger LOGGER = Logger.getLogger(Tracker.class);

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
     * Connection.
     */
    private Connection con = null;

    /**
     * Prepare statement.
     */
    private PreparedStatement ps = null;

    /**
     * Constructor.
     *
     * @param url      url.
     * @param userName name.
     * @param password password.
     */
    public Tracker(final String url, final String userName, final String password) {
        this.url = url;
        this.userName = userName;
        this.password = password;
    }

    /**
     * Create table from file.
     */
    public void createTable() {
        InputStream is = Tracker.class.getClass().getResourceAsStream("/createTables.sql");
        this.openConnect();
        try {
            ps = con.prepareStatement(ReadFile.readFile(is));
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.closeConnect();
            this.closePrepareStatement();
        }
    }

    /**
     * Drop table.
     *
     * @param tableName name.
     */
    public void dropTable(final String tableName) {
        this.openConnect();
        try {
            ps = con.prepareStatement(String.format("DROP TABLE %s", tableName));
            ps.executeQuery();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.closePrepareStatement();
            this.closeConnect();
        }
    }

    /**
     * Get task.
     *
     * @param id Id task to be returned
     * @return task.
     */
    public Task getTask(final int id) {
        Task task = null;
        this.openConnect();
        try {
            ps = con.prepareStatement("SELECT * FROM task WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    task = new Task(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            closePrepareStatement();
            closeConnect();
        }
        return task;
    }

    /**
     * Get comment.
     *
     * @param id Id comment to be returned
     * @return comment.
     */
    public Comment getComment(final int id) {
        Comment comment = null;
        this.openConnect();
        try {
            ps = con.prepareStatement("SELECT * FROM comments WHERE id = ?");
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    comment = new Comment(rs.getInt("id"), rs.getInt("task_id"),
                            rs.getTimestamp("create_date").getTime(), rs.getString("comment"));
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.closePrepareStatement();
            this.closeConnect();
        }
        return comment;
    }

    /**
     * Get all task.
     *
     * @return all task to table.
     */
    public List<Task> getAllTask() {
        List<Task> tasks = new ArrayList<>();
        this.openConnect();
        try {
            ps = con.prepareStatement("SELECT * FROM task");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Task task = new Task(rs.getInt("id"), rs.getString("name"),
                            rs.getString("description"), rs.getTimestamp("create_date").getTime());
                    tasks.add(task);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.closePrepareStatement();
            this.closeConnect();
        }
        return tasks;
    }

    /**
     * Get all comment to task.
     *
     * @param taskId task id.
     * @return comment to task.
     */
    public List<Comment> getAllCommentToTask(final int taskId) {
        List<Comment> comments = new ArrayList<>();
        this.openConnect();
        try {
            ps = con.prepareStatement("SELECT * FROM comments WHERE task_id = ?");
            ps.setInt(1, taskId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Comment comment = new Comment(rs.getInt("id"), rs.getInt("task_id"),
                            rs.getTimestamp("create_date").getTime(), rs.getString("comment"));
                    comments.add(comment);
                }
            }
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        } finally {
            this.closePrepareStatement();
            this.closeConnect();
        }
        return comments;
    }

    /**
     * Open connect to DB.
     */
    private void openConnect() {
        if (con == null) {
            try {
                con = DriverManager.getConnection(this.url, this.userName, this.password);
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Close connect to DB.
     */
    private void closeConnect() {
        if (this.con != null) {
            try {
                this.con.close();
                this.con = null;
            } catch (SQLException e) {
                LOGGER.error(e.getMessage(), e);
            }
        }
    }

    /**
     * Close prepare statement.
     */
    private void closePrepareStatement() {
        try {
            this.ps.close();
        } catch (SQLException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * Get.
     *
     * @return url.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Get.
     *
     * @return user name.
     */
    public String getUserName() {
        return userName;
    }

    /**
     * Get.
     *
     * @return password.
     */
    public String getPassword() {
        return password;
    }
}