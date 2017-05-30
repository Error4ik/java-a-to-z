package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Comment;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Добавляет кмментарий к задаче.
 */
public class AddCommentToTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public AddCommentToTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Добавление комментария).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the task ID to add a comment: ").trim());
        for (Task task : tracker.getAllTasks()) {
            if (task.getId() == id) {
                final Comment comment = new Comment(inputData.getInput("Enter comment to task: ").trim());
                tracker.addCommentTask(task, comment);
                System.out.println("Comment added.");
            }
        }
    }
}
