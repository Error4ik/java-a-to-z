package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Task;
import ru.job4j.view.ConsoleView;

import java.util.List;

/**
 * Query show all task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ShowAllTask extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public ShowAllTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final StringBuilder sb = new StringBuilder();
        final List<Task> tasks = tracker.getAllTask();
        for (Task task : tasks) {
            sb.append(task).append(System.getProperty("line.separator"));
        }
        new ConsoleView().execute(sb.toString());
    }
}
