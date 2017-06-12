package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Task;

/**
 * Query Edit task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class UpdateTask extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public UpdateTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final int taskID = Integer.parseInt(inputData.getInput("Enter the task ID that you want to edit: ").trim());
        final String name = inputData.getInput("Enter new task name: ").trim();
        final String description = inputData.getInput("Enter new desc: ").trim();
        final Task task = new Task(taskID, name, description, 0);
        tracker.updateTask(task);
    }
}
