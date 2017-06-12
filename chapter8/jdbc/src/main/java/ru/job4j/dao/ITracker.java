package ru.job4j.dao;

import ru.job4j.models.Comment;
import ru.job4j.models.Task;

import java.util.List;

/**
 * Interface menu_tracker.
 *
 * @author Alexey Voronin.
 * @since 10.06.2017.
 */
public interface ITracker {

    /**
     * Add new task to DataBase.
     *
     * @param task task.
     * @return id.
     */
    int addTask(final Task task);

    /**
     * Add new comment ot task.
     *
     * @param comment comment.
     * @return id.
     */
    int addComment(final Comment comment);

    /**
     * Get task.
     *
     * @param taskID Id task to be returned
     * @return task.
     */
    Task getTaskById(final int taskID);

    /**
     * Get comment.
     *
     * @param commentID Id comment to be returned
     * @return comment.
     */
    Comment getCommentById(final int commentID);

    /**
     * Get all task.
     *
     * @return all task to table.
     */
    List<Task> getAllTask();

    /**
     * Get all comment to task.
     *
     * @param taskID task id.
     * @return comment to task.
     */
    List<Comment> getAllCommentToTask(final int taskID);

    /**
     * Remove task by id.
     *
     * @param taskID id.
     * @return true.
     */
    boolean removeTaskById(final int taskID);

    /**
     * Remove comment by ud.
     *
     * @param commentID id.
     * @return true.
     */
    boolean removeComment(final int commentID);

    /**
     * Update task.
     *
     * @param task updated task.
     * @return true.
     */
    boolean updateTask(final Task task);

    /**
     * Get task by name.
     *
     * @param name name.
     * @return task.
     */
    List<Task> getTaskByName(final String name);

    /**
     * Get task by coincidence.
     *
     * @param coincidence coincidence.
     * @return task.
     */
    List<Task> getTaskByCoincidence(final String coincidence);
}
