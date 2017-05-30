package ru.job4j.update_tracker.action;

import ru.job4j.update_tracker.input.Input;
import ru.job4j.update_tracker.models.Task;
import ru.job4j.update_tracker.tracker.Tracker;

/**
 * Выводит задачи по фильтру (имя).
 */
public class FilteredTaskByName extends BaseAction {

    /**
     * конструетор принимает.
     *
     * @param id   номер задачи.
     * @param name название задачи.
     */
    public FilteredTaskByName(final String id, final String name) {
        super(id, name);
    }

    /**
     * Метод выполняет логику пункта меню (Фильтр задач).
     *
     * @param tracker   трекер.
     * @param inputData выбор пользователя.
     */
    @Override
    public void execute(final Tracker tracker, final Input inputData) {
        final String filter = inputData.getInput("Enter filter: ").trim();
        final Task[] tasks = tracker.filteredTaskToName(filter);
        System.out.println("Filter by name: " + filter);
        dividingLine();
        for (Task task : tasks) {
            System.out.print(task.toString());
        }
        dividingLine();
    }

    /**
     * Выводит разделительную полосу.
     */
    public void dividingLine() {
        System.out.println("=========================");
    }
}
