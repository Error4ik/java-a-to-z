package ru.job4j.models;

import java.util.Date;
import java.util.Objects;

/**
 * Task model.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class Task {

    /**
     * Task id.
     */
    private final int id;

    /**
     * Task name.
     */
    private final String name;

    /**
     * Task description.
     */
    private final String description;

    /**
     * Date of task creation.
     */
    private Date createDate;

    /**
     * Constructor.
     *
     * @param id          task id.
     * @param name        task name.
     * @param description task description.
     * @param date create date.
     */
    public Task(final int id, final String name, final String description, final long date) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.createDate = new Date(date);
    }

    /**
     * Get.
     *
     * @return name.
     */
    public String getName() {
        return name;
    }

    /**
     * Get.
     *
     * @return description.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Get.
     *
     * @return createDate.
     */
    public Date getCreateDate() {
        return this.createDate;
    }

    /**
     * Get.
     *
     * @return task id.
     */
    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Task)) {
            return false;
        }
        Task task = (Task) o;
        return getId() == task.getId()
                && Objects.equals(getName(), task.getName())
                && Objects.equals(getDescription(), task.getDescription())
                && Objects.equals(getCreateDate(), task.getCreateDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), getCreateDate());
    }

    @Override
    public String toString() {
        return String.format("{Task id: %s}, {Task name: %s}, {Task description: %s}, {Create date: %s}",
                this.getId(), this.getName(), this.getDescription(), this.getCreateDate());
    }
}
