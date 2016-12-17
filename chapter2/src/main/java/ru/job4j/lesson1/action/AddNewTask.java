package ru.job4j.lesson1.action;

import ru.job4j.lesson1.input.Input;
import ru.job4j.lesson1.models.Task;
import ru.job4j.lesson1.tracker.Tracker;

/**
 * Добавляет новую задачу в трекер.
 */
public class AddNewTask extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public AddNewTask(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Добавления задачи).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        String nameTask = inputData.getInput("Enter the name new task: ").trim();
        String descTask = inputData.getInput("Enter the description new task: ").trim();
        if (!nameTask.equals("")) {
            final Task task = new Task(nameTask, descTask);
            tracker.addTask(task);
            System.out.println("Task " + "{" + task.getName() + "}" + " added.");
        }
    }
}
