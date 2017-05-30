package ru.job4j.update_tracker.tracker;


import ru.job4j.update_tracker.action.AddNewTask;
import ru.job4j.update_tracker.action.RemoveTask;
import ru.job4j.update_tracker.action.AddCommentToTask;
import ru.job4j.update_tracker.action.RemoveCommentTask;
import ru.job4j.update_tracker.action.EditTask;
import ru.job4j.update_tracker.action.ExitTrackerProgram;
import ru.job4j.update_tracker.action.ShowAllCommentTask;
import ru.job4j.update_tracker.action.ShowAllTask;
import ru.job4j.update_tracker.action.FilteredTaskByName;
import ru.job4j.update_tracker.action.Action;
import ru.job4j.update_tracker.input.Input;

/**
 * Класс для управления трекером.
 */
public class MenuTracker {

    /**
     * Хранит размер массива действий по умолчанию.
     */
    private final int defaultSize = 9;

    /**
     * Хранит действия которые предоставляются пользователю.
     */
    private Action[] actions;

    /**
     * трекер которым данный класс управляет.
     */
    private final Tracker tracker;

    /**
     * запрос и получение ввода для пользователя.
     */
    private final Input inputData;

    /**
     * содержит колличество действий в трекере.
     */
    private int countAction = 0;

    /**
     * Конструктор принимает на вход.
     *
     * @param tracker   трекер которым нужно управлять.
     * @param inputData ввод, вывод пользователя.
     */
    public MenuTracker(Tracker tracker, Input inputData) {
        this.tracker = tracker;
        this.inputData = inputData;
        this.actions = new Action[defaultSize];
    }

    /**
     * Заполняет массив действий, действиями доступными в трекере.
     */
    public void fillAction() {
        actions[countAction++] = new AddNewTask("1", "Add new task");
        actions[countAction++] = new RemoveTask("2", "Remove Task");
        actions[countAction++] = new EditTask("3", "Edit Task");
        actions[countAction++] = new AddCommentToTask("4", "Add Comment Task");
        actions[countAction++] = new RemoveCommentTask("5", "Remove Comment task");
        actions[countAction++] = new ShowAllTask("6", "Show All Task");
        actions[countAction++] = new ShowAllCommentTask("7", "Show All Comment Task");
        actions[countAction++] = new FilteredTaskByName("8", "Filtered Task By Name");
        actions[countAction++] = new ExitTrackerProgram("9", "Exit Tracker");
    }

    /**
     * Метод добавляет в массив все пункты меню, что бы пользователь не выходил за них.
     *
     * @return возвращает массив с пунктами меню.
     */
    public int[] getMenuNumbers() {
        final int[] menuNumbers = new int[this.getCountAction()];
        for (int i = 0; i < this.getCountAction(); i++) {
            menuNumbers[i] = Integer.parseInt(actions[i].getId());
        }
        return menuNumbers;
    }

    /**
     * Метод для добавления действия.
     *
     * @param action Действие которое нужно добавить в трекер.
     * @return возвращает true.
     */
    public boolean addAction(final Action action) {
        if (actions.length == countAction) {
            final Action[] tmp = actions;
            actions = new Action[tmp.length * 2];
            System.arraycopy(tmp, 0, actions, 0, tmp.length);
        }
        actions[countAction++] = action;
        return true;
    }

    /**
     * Показывает пункты меню доступные для пользователя.
     */
    public void showMenu() {
        for (int i = 0; i < this.getCountAction(); i++) {
            System.out.println(actions[i].showItem());
        }
    }

    /**
     * Выбирает действие из массива действий в зависимости от выбора пользователя.
     *
     * @param value норме действия которое нужно выполнить.
     */
    public void select(final int value) {
        for (int i = 0; i < this.getCountAction(); i++) {
            if (Integer.parseInt(this.actions[i].getId()) == value) {
                this.actions[i].execute(tracker, inputData);
            }
        }
    }

    /**
     * геттер.
     *
     * @return количество действий в трекере.
     */
    public int getCountAction() {
        return countAction;
    }

    /**
     * Метод возвращает массив всех действий.
     *
     * @return массив действий.
     */
    public Action[] getAllAction() {
        final Action[] array = new Action[this.getCountAction()];
        System.arraycopy(actions, 0, array, 0, this.getCountAction());
        return array;
    }
}