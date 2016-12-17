package ru.job4j.lesson1.action;

/**
 * Абстрактный базовый клас для всех действий, реализует интерфейс Action.
 */
public abstract class BaseAction implements Action {

    /**
     * Храни название действия.
     */
    private final String name;

    /**
     * храние id действия.
     */
    private final String id;

    /**
     * конструктор получает на вход.
     * @param id id действия.
     * @param name и название действия.
     */
    public BaseAction(String id, String name) {
        this.id = id;
        this.name = name;
    }

    /**
     * Возвращает номер под которым данное действие находится в меню.
     * @return номер.
     */
    @Override
    public int getItemNumber() {
        return Integer.parseInt(this.getId());
    }

    /**
     * Метод для отображения порядкового номера и названия действия.
     * @return строку с номером и именем действия.
     */
    @Override
    public String showItem() {
        return String.format("%s. %s", this.getId(), this.getName());
    }

    /**
     * геттер для названия действия.
     * @return название.
     */
    public String getName() {
        return this.name;
    }

    /**
     * геттер для id действия.
     * @return id.
     */
    public String getId() {
        return this.id;
    }
}