package ru.job4j.lesson1.startCLI;

import ru.job4j.lesson1.models.Task;
import ru.job4j.lesson1.tracker.Tracker;

/**
 * Класс для запуска.
 */
public class StartUI {

    /**
     * Запускает трекер.
     * @param args массив аргументов.
     */
    public static void main(String[] args) {
        Tracker tracker = new Tracker();
        tracker.addTask(new Task("Hello", "World"));
        for (Task task : tracker.getAllTasks()) {
            System.out.println("Name: " + task.getName() + ", Description: " + task.getDescription());
        }
    }
}
