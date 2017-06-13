package ru.job4j.menu_tracker;

import ru.job4j.action.Action;
import ru.job4j.action.AddNewTask;
import ru.job4j.action.AddComment;
import ru.job4j.action.ExitTrackerProgram;
import ru.job4j.action.FilterByCoincidence;
import ru.job4j.action.FilterTaskByName;
import ru.job4j.action.RemoveComment;
import ru.job4j.action.RemoveTask;
import ru.job4j.action.ShowAllCommentToTask;
import ru.job4j.action.ShowAllTask;
import ru.job4j.action.UpdateTask;
import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.view.ConsoleView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for menu_tracker management.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class MenuTracker {

    /**
     * Actions that are provided to the user.
     */
    private List<Action> actions;

    /**
     * Tracker.
     */
    private final ITracker tracker;

    /**
     * User input.
     */
    private final Input inputData;

    /**
     * Constructor.
     *
     * @param tracker   menu_tracker.
     * @param inputData user input.
     */
    public MenuTracker(final ITracker tracker, final Input inputData) {
        this.tracker = tracker;
        this.inputData = inputData;
        this.actions = new ArrayList<>();
    }

    /**
     * Fills an array of actions, actions available in the menu_tracker.
     */
    public void fillAction() {
        this.addAction(new AddNewTask("1", "Add task"));
        this.addAction(new RemoveTask("2", "Remove Task"));
        this.addAction(new UpdateTask("3", "Edit Task"));
        this.addAction(new AddComment("4", "Add Comment"));
        this.addAction(new RemoveComment("5", "Remove Comment"));
        this.addAction(new ShowAllTask("6", "Show All Task"));
        this.addAction(new ShowAllCommentToTask("7", "Show All Comment Task"));
        this.addAction(new FilterTaskByName("8", "Filtered Task By Name"));
        this.addAction(new FilterByCoincidence("9", "Filter by coincidence"));
        this.addAction(new ExitTrackerProgram("0", "Exit Tracker"));
    }

    /**
     * Add action to menu_tracker.
     *
     * @param action action.
     * @return true.
     */
    public boolean addAction(final Action action) {
        return this.actions.add(action);
    }

    /**
     * Displays menu items available to the user.
     */
    public void showMenu() {
        StringBuilder sb = new StringBuilder();
        for (Action action : actions) {
            sb.append(action.showItem()).append(System.getProperty("line.separator"));
        }
        new ConsoleView().execute(sb.toString());
    }

    /**
     * Selects the action based on the user's choice.
     *
     * @param value Number of action to be performed.
     */
    public void select(final int value) {
        actions.stream().filter(action -> Integer.parseInt(action.getId()) == value).forEach(action -> {
            action.execute(tracker, inputData);
        });
    }

    /**
     * Get.
     *
     * @return list.
     */
    public List<Action> getActions() {
        return actions;
    }
}
