package ru.job4j.update_tracker.models;

import java.util.Date;
import java.util.Objects;

/**
 * Comment model.
 */
public class Comment {

    /**
     * Comment id.
     */
    private final int id;

    /**
     * Task id.
     */
    private final int taskID;

    /**
     * Date of comment creation.
     */
    private final Date createDate;

    /**
     * Comment.
     */
    private final String comment;

    /**
     * Constructor.
     *
     * @param id      comment id.
     * @param taskID  task id to comment
     * @param date    create date.
     * @param comment comment.
     */
    public Comment(final int id, final int taskID, final long date, final String comment) {
        this.id = id;
        this.taskID = taskID;
        this.createDate = new Date(date);
        this.comment = comment;
    }

    /**
     * Get.
     *
     * @return comment.
     */
    public String getComment() {
        return comment;
    }

    /**
     * Get.
     *
     * @return create date.
     */
    public Date getCreateDate() {
        return createDate;
    }

    /**
     * Get.
     *
     * @return taskID;
     */
    public int getTaskID() {
        return taskID;
    }

    /**
     * Get.
     * @return comment id;
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Comment)) {
            return false;
        }
        Comment comment1 = (Comment) o;
        return id == comment1.id
                && getTaskID() == comment1.getTaskID()
                && Objects.equals(getCreateDate(), comment1.getCreateDate())
                && Objects.equals(getComment(), comment1.getComment());
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, getTaskID(), getCreateDate(), getComment());
    }

    @Override
    public String toString() {
        return String.format("{ID: %s}, {TaskID: %s}, {Create date: %s}, {Comment: %s}", this.getId(),
                this.getTaskID(), this.getCreateDate(), this.getComment());
    }
}