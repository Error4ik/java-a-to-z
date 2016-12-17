package ru.job4j.lesson1.action;

import ru.job4j.lesson1.input.Input;
import ru.job4j.lesson1.models.Comment;
import ru.job4j.lesson1.models.Task;
import ru.job4j.lesson1.tracker.Tracker;

/**
 * Выводит комментарии к задаче.
 */
public class ShowAllCommentTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public ShowAllCommentTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Показать комментарии к задаче).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the task ID at which you want to show comments: ").trim());
        for (Task task : tracker.getAllTasks()) {
            if (task.getId() == id) {
                System.out.println("Comments to the task id: " + task.getName());
                dividingLine();
                if (task.getCountComment() == 0) {
                    System.out.println("In this task, there are no comments.");
                } else {
                    for (Comment comment : task.getAllComments()) {
                        System.out.println(comment);
                    }
                }
            }
        }
        //dividingLine();
    }

    /**
     * Выводит на консоль линию разделяющую вывод пунктов меню для читаемости.
     */
    public void dividingLine() {
        System.out.println("=========================");
    }
}
