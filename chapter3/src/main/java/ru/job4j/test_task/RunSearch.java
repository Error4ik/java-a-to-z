package ru.job4j.test_task;

import ru.job4j.test_task.exception.InvalidKeyException;

import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Класс запускает поиск файла после проверке ключей.
 */
public class RunSearch {

    /**
     * Массив аргументов переданных в программу.
     */
    private String[] args;

    /**
     * Конструктор.
     *
     * @param args массив аргументов.
     */
    public RunSearch(final String[] args) {
        this.args = args;
    }

    /**
     * Запуская поиск файла, предварительно проверив что параметры переданные программе правильные.
     *
     * @throws InvalidKeyException Если ключь или ключи не валидны.
     */
    public void runSearch() throws InvalidKeyException {
        Validator validator = new Validator(args);
        if (validator.isCorrectKey()) {
            Path directory = Paths.get(validator.getDirectory());
            String fileName = validator.getFileName();
            String key = validator.getTypeSearch();
            String outFileName = validator.getOutFile();
            FindFile findFile = new FindFile(directory, fileName, key, outFileName);
            findFile.findFileInDirectory();
        } else {
            validator.showConditions();
            throw new InvalidKeyException("You entered not right key, try again.");
        }
    }

    /**
     * Точка входа.
     *
     * @param args аргументы.
     * @throws InvalidKeyException ошибка ключей.
     */
    public static void main(String[] args) throws InvalidKeyException {
        RunSearch search = new RunSearch(args);
        search.runSearch();
    }
}
