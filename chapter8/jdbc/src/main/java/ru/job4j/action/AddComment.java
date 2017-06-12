package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Comment;

/**
 * Query Adds comment to task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class AddComment extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public AddComment(final String id, final String name) {
        super(id, name);
    }


    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        int taskID = Integer.parseInt(inputData.getInput("Enter the task ID to add a comment: "));
        String text = inputData.getInput("Enter commentary: ").trim();
        final Comment comment = new Comment(0, taskID, System.currentTimeMillis(), text);
        tracker.addComment(comment);
    }
}
