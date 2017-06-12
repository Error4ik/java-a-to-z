package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Task;
import ru.job4j.view.ConsoleView;

import java.util.List;

/**
 * Query Filter by task name.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class FilterTaskByName extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public FilterTaskByName(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final StringBuilder sb = new StringBuilder();
        final String name = inputData.getInput("Enter name: ").trim();
        final List<Task> tasks = tracker.getTaskByName(name);
        for (Task task : tasks) {
            sb.append(task).append(System.getProperty("line.separator"));
        }
        new ConsoleView().execute(sb.toString());
    }
}
