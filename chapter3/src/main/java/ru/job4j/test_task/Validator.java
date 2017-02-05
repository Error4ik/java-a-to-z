package ru.job4j.test_task;

import com.google.common.base.Joiner;

/**
 * Класс для проверки вводимых ключей.
 */
public class Validator {

    /**
     * Разделитель для строк.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Массив аргументов.
     */
    private final String[] args;

    /**
     * Дириктория в которой нужно начать поиск.
     */
    private String directory;

    /**
     * Файл который нужо найти.
     */
    private String fileName;

    /**
     * Тип поиска, по маске или полное совпадение.
     */
    private String typeSearch;

    /**
     * Файл в который нужно записать результаты поиска.
     */
    private String outFile;

    /**
     * Конструктор.
     * @param args массив аргументов.
     */
    public Validator(final String[] args) {
        this.args = args;
    }

    /**
     * Проверяет валидность ключей.
     * @return tru если ключи валидны, если нет то false.
     */
    public boolean isCorrectKey() {
        boolean correct = false;
        if (args.length == 7 && args[0].equals("-d") && args[2].equals("-n") && args[5].equals("-o")
                && (args[4].equals("-m") || args[4].equals("-f"))) {

            this.init();
            correct = true;
        }

        return correct;
    }

    /**
     * Выводит подсказку если была допущена ошибка в аргументах.
     */
    public void showConditions() {
        System.out.printf(Joiner.on(separator).join("Line arguments must be of the form:   -d dir -n searchFileName -m -o saveFileName",
                "{-d} key directory",
                "{dir} directory to search file",
                "{-n} key file",
                "{file} file name to search",
                "{-m or -f}  type search key (-m = mask search) (-f = full name search)",
                "{-o} key file",
                "{file} file name to save result search."));
        System.out.printf("%sYour line arguments:    %s %s %s %s %s %s %s", separator, args[0], args[1], args[2],
                args[3], args[4], args[5], args[6]);
    }

    /**
     * Геттер.
     * @return дирикторию в которой искать файл.
     */
    public String getDirectory() {
        return directory;
    }

    /**
     * Геттер.
     * @return имя файла для поиска.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Геттер.
     * @return Тип поиска (по маске или по полному совпадению).
     */
    public String getTypeSearch() {
        return typeSearch;
    }

    /**
     * Геттер.
     * @return Имя файла в который нужно записать результат.
     */
    public String getOutFile() {
        return outFile;
    }

    /**
     * Иникиализирует переменные.
     */
    private void init() {
        this.directory = args[1];
        this.fileName = args[3];
        this.typeSearch = args[4];
        this.outFile = args[6];
    }
}
