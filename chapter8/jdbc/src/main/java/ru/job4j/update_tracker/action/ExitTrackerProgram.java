package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.tracker.Tracker;
import ru.job4j.update_tracker.view.ConsoleView;

/**
 * Exit from program.
 */
public class ExitTrackerProgram extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public ExitTrackerProgram(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        new ConsoleView().execute(String.format("Exit program%s", System.getProperty("line.separator")));
    }
}
