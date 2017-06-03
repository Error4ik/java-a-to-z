package ru.job4j.action;

import ru.job4j.input.Input;
import ru.job4j.tracker.Tracker;
import ru.job4j.view.ConsoleView;

/**
 * Exit from program.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
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
