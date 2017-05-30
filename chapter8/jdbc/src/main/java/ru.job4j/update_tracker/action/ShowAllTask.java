package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * выводит все задачи в трекере.
 */
public class ShowAllTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public ShowAllTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Показать все задачи).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        System.out.println("Task Tracker: " + tracker.getCountTask());
        dividingLine();
        if (tracker.getCountTask() == 0) {
            System.out.println("At this time the task list is empty, please add a task.");
        } else {
            for (Task task : tracker.getAllTasks()) {
                System.out.print(task.toString());
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
