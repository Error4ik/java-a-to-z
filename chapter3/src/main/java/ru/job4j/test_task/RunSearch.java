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
     * Разделитель для строк.
     */
    private String separator = System.getProperty("line.separator");

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
        if (args.length == 7 && args[0].equals("-d") && args[2].equals("-n") && args[5].equals("-o")
                && (args[4].equals("-m") || args[4].equals("-f"))) {

            Path directory = Paths.get(args[1]);
            String fileName = args[3];
            String key = args[4];
            String outFileName = args[6];
            FindFile findFile = new FindFile(directory, fileName, key, outFileName);
            findFile.findFileInDirectory();
        } else {
            showConditions();
            throw new InvalidKeyException("You entered not right key, try again.");
        }
    }

    /**
     * Выводит подсказку если была допущена ошибка в аргументах.
     */
    private void showConditions() {
        System.out.printf("%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s%s",
                "Line arguments must be of the form:   -d dir -n searchFileName -m -o saveFileName", separator,
                "{-d} key directory", separator,
                "{dir} directory to search file", separator,
                "{-n} key file", separator,
                "{file} file name to search", separator,
                "{-m or -f}  type search key (-m = mask search) (-f = full name search)", separator,
                "{-o} key file", separator,
                "{file} file name to save result search.", separator);
        System.out.printf("%sYour line arguments:    %s %s %s %s %s %s %s", separator, args[0], args[1], args[2],
                args[3], args[4], args[5], args[6]);
    }

    /**
     * Точка входа.
     *
     * @param args аргументы.
     * @throws InvalidKeyException      ошибка ключей.
     */
    public static void main(String[] args) throws InvalidKeyException {
        RunSearch search = new RunSearch(args);
        search.runSearch();
    }
}
