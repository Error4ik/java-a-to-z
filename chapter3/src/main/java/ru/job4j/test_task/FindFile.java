package ru.job4j.test_task;

import ru.job4j.test_task.settings.Settings;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс для поиска файла в каталоге.
 */
public class FindFile {

    /**
     * Разделить для строк.
     */
    private final String sepLine = System.getProperty("line.separator");

    /**
     * Разделитель дирикторий.
     */
    private final String sepDir = System.getProperty("file.separator");

    /**
     * Проверяет на совпадение строки с шаблоном.
     */
    private Matcher matches;

    /**
     * Шаблон для поиска.
     */
    private Pattern pattern;

    /**
     * Каталов в котором нужно произвести поиск.
     */
    private Path directory;

    /**
     * Имя файла который нужно найти.
     */
    private String searchFileName;

    /**
     * Тип поиска, полное совпадение, маска.
     */
    private String key;

    /**
     * Файл в который записать результат поиска.
     */
    private String outFileName;

    /**
     * Формирует строку.
     */
    private StringBuilder sb = new StringBuilder();

    /**
     * Параметры из файла с настройками.
     */
    private Settings settings = new Settings();

    /**
     * Конструктор.
     *
     * @param directory   дириктория в которой нужно найти файл.
     * @param fileName    файл который нужно найти.
     * @param key         тип поиска (Полное совпадение или по Маске).
     * @param outFileName Файл в который записывается реультат.
     */
    public FindFile(Path directory, String fileName, String key, String outFileName) {
        this.directory = directory;
        this.searchFileName = fileName;
        this.key = key;
        this.outFileName = outFileName;
    }

    /**
     * Метод поиска файлов в заданной дириктории.
     * В заданной пользователем дириктории ищет нужный пользователю файл.
     */
    public void findFileInDirectory() {
        this.pattern = Pattern.compile(getFileName(this.searchFileName, this.key));
        try {
            Files.walkFileTree(this.directory, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    matches = pattern.matcher(String.valueOf(file.getFileName()));
                    if (matches.matches()) {
                        sb.append(file.toAbsolutePath()).append(sepLine);
                    }
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
                    return FileVisitResult.SKIP_SUBTREE;
                }
            });
            new WriteFile().writeResultToFile(this.outFileName, sb.toString());
            System.out.printf("%s%s%s%s%s%s", "File search complete",
                    "Search results stored in the file: ",
                    this.settings.getValue("log.file.directory"), sepDir, outFileName, sepLine);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод в зависимости от ключа введенного пользователем формирует строку для шаблона.
     *
     * @param fileName Имя файла.
     * @param key      тип ключа.
     * @return готовую строку для шаблона.
     */
    private String getFileName(final String fileName, final String key) {
        StringBuilder sb = new StringBuilder();
        String result = null;
        if (key.equalsIgnoreCase("-m")) {
            sb.append("^");
            char[] array = fileName.toCharArray();
            for (char c : array) {
                if (c == '*') {
                    sb.append(".*");
                } else if (c == '?') {
                    sb.append(".");
                } else if (c == '.') {
                    sb.append("\\.");
                } else {
                    sb.append(c);
                }
            }
            sb.append("$");
            result = sb.toString();
        } else if (key.equalsIgnoreCase("-f")) {
            result = fileName;
        }
        return result;
    }
}
