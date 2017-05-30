package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Comment;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Удаляет комментарий из задачи.
 */
public class RemoveCommentTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public RemoveCommentTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Удаление комментария).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the task ID in which you want to delete a comment: ").trim());
        for (Task task : tracker.getAllTasks()) {
            if (task.getId() == id) {
                final int commentId = Integer.parseInt(inputData.getInput("Enter the ID you want to delete a comment: ").trim());
                for (Comment comment : task.getAllComments()) {
                    if (comment.getId() == commentId) {
                        task.removeComment(comment);
                        System.out.println("Comment has been deleted");
                    }
                }
            }
        }
    }
}
