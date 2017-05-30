package ru.job4j.update_tracker.input;

import ru.job4j.update_tracker.exception.MenuOutException;

/**
 * Класс для автоматических тестов.
 */
public class StubInput implements Input {

    /**
     * Массив имитирующий пользовательский ввод.
     */
    private final String[] answers;

    /**
     * Индес в массиве пользовательского ввода.
     */
    private int position = 0;

    /**
     * Конструктор инициализирует массив.
     *
     * @param array массив.
     */
    public StubInput(final String[] array) {
        this.answers = array;
    }

    /**
     * Метод возвращает пользовательский ввод из массива.
     *
     * @param msg сообщение для пользователя.
     * @return возвращает строку из массива по индексу.
     */
    @Override
    public String getInput(String msg) {
        validate();
        return answers[position++];
    }

    /**
     *
     * @throws MenuOutException исключение.
     */
    private void validate() throws MenuOutException {
        int validateMenu = Integer.parseInt(answers[0]);
        final int countMenuNumber = 9;
        if (validateMenu > countMenuNumber) {
            throw new MenuOutException("Error");
        }
    }
}
