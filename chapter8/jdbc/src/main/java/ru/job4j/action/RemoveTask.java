package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;

/**
 * Query Remove task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class RemoveTask extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public RemoveTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final int taskID = Integer.parseInt(inputData.getInput("Enter the ID for the task you want to delete: ").trim());
        tracker.removeTaskById(taskID);
    }
}
