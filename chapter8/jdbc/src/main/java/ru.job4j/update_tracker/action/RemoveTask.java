package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Удаляет задачу.
 */
public class RemoveTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public RemoveTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Удаление задачи).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the ID for the task you want to delete: ").trim());
        for (Task task : tracker.getAllTasks()) {
            if (task.getId() == id) {
                tracker.removeTask(task);
                System.out.println("Task: " + "{" + task.getName() + "}" + " Deleted");
            }
        }
    }
}