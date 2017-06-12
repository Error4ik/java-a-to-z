package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;

/**
 * Query Remove comment to task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class RemoveComment extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public RemoveComment(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final int commentID = Integer.parseInt(
                inputData.getInput("Enter the ID you want to delete a comment: ").trim());
        tracker.removeComment(commentID);
    }
}
