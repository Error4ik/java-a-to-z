package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Редактирует задачу.
 */
public class EditTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public EditTask(String id, String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Редактирование задачи).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final int id = Integer.parseInt(inputData.getInput("Enter the task ID that you want to edit: ").trim());
        for (Task task : tracker.getAllTasks()) {
            if (task.getId() == id) {
                String name = inputData.getInput("Enter a new name for the task: ").trim();
                if (!name.equals("")) {
                    task.setName(name);
                    task.setDescription(inputData.getInput("Enter a new description for the task: ").trim());
                    System.out.println(tracker.editTask(task));
                    System.out.println("The task is edited and saved.");
                }
            }
        }
    }
}
