package ru.job4j.lesson5;


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
     * @return возвращает строку из массива по индексу.
     */
    @Override
    public String getInput() {
        return answers[position++];
    }
}
