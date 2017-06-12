package ru.job4j.action;

import ru.job4j.dao.ITracker;
import ru.job4j.input.Input;
import ru.job4j.models.Comment;
import ru.job4j.view.ConsoleView;

import java.util.List;

/**
 * Query show comment to task.
 *
 * @author Alexey Voronin.
 * @since 03.06.2017.
 */
public class ShowAllCommentToTask extends BaseAction {

    /**
     * Constructor.
     *
     * @param id   action id.
     * @param name action name.
     */
    public ShowAllCommentToTask(final String id, final String name) {
        super(id, name);
    }

    @Override
    public void execute(final ITracker tracker, final Input inputData) {
        final int taskID = Integer.parseInt(inputData.getInput(
                "Enter the task id of the comments you want to receive: ").trim());
        final StringBuilder sb = new StringBuilder();
        List<Comment> comments = tracker.getAllCommentToTask(taskID);
        for (Comment comment : comments) {
            sb.append(comment).append(System.getProperty("line.separator"));
        }
        new ConsoleView().execute(sb.toString());
    }
}
