package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Task;
import ru.job4j.view.ConsoleView;

import java.util.List;

/**
 * Query Filter by coincidence.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class FilterByCoincidence extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public FilterByCoincidence(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        StringBuilder sb = new StringBuilder();
        final String filter = inputData.getInput("Enter filter: ").trim();
        List<Task> tasks = tracker.getTaskByCoincidence(filter);
        for (Task task : tasks) {
            sb.append(task).append(System.getProperty("line.separator"));
        }
        new ConsoleView().execute(sb.toString());
    }
}
