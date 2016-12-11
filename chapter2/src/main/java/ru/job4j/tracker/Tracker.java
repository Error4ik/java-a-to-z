package ru.job4j.tracker;

import ru.job4j.tracker.task.Comment;
import ru.job4j.tracker.task.Task;

import java.util.Arrays;

/**
 * Класс содержит заявки.
 */
public class Tracker {

    /**
     * Размер массива заявок по умолчанию.
     */
    private static final int DEFAULT_TASK_COUNT = 3;

    /**
     * Массив для хранения заявок.
     */
    private Task[] tasks;

    /**
     * Колличество заявок.
     */
    private int countTask;

    /**
     * Конструктор создает массив для заявок размером по умолчанию.
     */
    public Tracker() {
        this.tasks = new Task[DEFAULT_TASK_COUNT];
    }

    /**
     * Добавление новой заявки.
     * @param task Задача которую нужно добавить.
     * @return Возвращает true если задача была добавлена.
     */
    public boolean addTask(final Task task) {
        if (this.tasks.length == getCountTask()) {
            final Task[] oldArray = this.tasks;
            this.tasks = new Task[this.tasks.length * 2];
            System.arraycopy(oldArray, 0, this.tasks, 0, oldArray.length);
        }
        this.tasks[this.countTask++] = task;
        return true;
    }

    /**
     * Удаление заявки.
     *
     * @param task заявка которую нужно удалить.
     * @return возвращает true если заявка была удалена, в противном случае false.
     */
    public boolean remove(final Task task) {
        boolean result = false;
        for (int i = 0; i < getCountTask(); i++) {
            if (this.tasks[i].equals(task)) {
                if (i != getCountTask() - 1) {
                    System.arraycopy(this.tasks, i + 1, this.tasks, i, getCountTask() - i - 1);
                }
                this.countTask--;
                result = true;
            }
        }
        return result;
    }

    /**
     * Добавляет комментарий к задаче.
     *
     * @param task    задача к которой нужно добавить комментарий.
     * @param comment коментарий.
     */
    public void addCommentTask(final Task task, final Comment comment) {
        for (int i = 0; i < this.getCountTask(); i++) {
            if (this.tasks[i].equals(task)) {
                this.tasks[i].addComment(comment);
            }
        }
    }

    /**
     * Метод меняет имя и описание у существующей задачи.
     *
     * @param task        задачу которую нужно отредавктировать.
     * @param name        новое имя задачу.
     * @param description новое описание задачи.
     * @return возвращает true если задачу удалось отредактировать, если нет то false.
     */
    public boolean editTask(final Task task, final String name, final String description) {
        boolean result = false;
        if (this.contains(task)) {
            task.setName(name);
            task.setDescription(description);
            result = true;
        }
        return result;
    }

    /**
     * Фильтрует задачи по имени.
     *
     * @param string строка по которой нужно отфильмтровать.
     * @return возвращает отфильтрованный массив.
     */
    public Task[] filteredTaskToName(final String string) {
        final Task[] filteredArray = new Task[this.countTask];
        int countIndex = 0;
        for (int i = 0; i < countTask; i++) {
            if (this.tasks[i].getName().contains(string)) {
                filteredArray[countIndex++] = this.tasks[i];
            }
        }
        return Arrays.copyOf(filteredArray, countIndex);
    }

    /**
     * Генерирует айди.
     * @return возвращает id.
     */
    public long generateId() {
        final int d = 1000;
        return System.currentTimeMillis() / d;
    }

    /**
     * Отображение списка всех заявок.
     *
     * @return массив задач.
     */
    public Task[] getAllTasks() {
        final Task[] arrayTask = new Task[this.countTask];
        System.arraycopy(this.tasks, 0, arrayTask, 0, arrayTask.length);
        return arrayTask;
    }

    /**
     * Метод проверят есть ли задача в списке.
     *
     * @param task зачадача которую нужно найти.
     * @return возвращает true если задача есть в списке, в протиивном случае false.
     */
    public boolean contains(final Task task) {
        boolean result = false;
        for (Task t : tasks) {
            if (task.equals(t)) {
                result = true;
            }
        }
        return result;
    }

    /**
     * @return возвращает количество задач.
     */
    public int getCountTask() {
        return this.countTask;
    }

    @Override
    public String toString() {
        return "Count Task: " + countTask;
    }
}