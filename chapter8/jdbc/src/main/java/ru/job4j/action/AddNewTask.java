package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Task;

/**
 * Query Adds new task to DB.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class AddNewTask extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public AddNewTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final String name = inputData.getInput("Enter the name new task: ").trim();
        final String description = inputData.getInput("Enter the description new task: ").trim();
        final Task task = new Task(0, name, description, System.currentTimeMillis());
        tracker.addTask(task);
    }
}
