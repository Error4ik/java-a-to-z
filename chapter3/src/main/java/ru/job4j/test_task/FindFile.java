package ru.job4j.test_task;


import ru.job4j.test_task.settings.Settings;

import java.io.IOException;
import java.io.OutputStream;
import java.io.InputStream;
import java.io.File;
import java.nio.file.Path;
import java.nio.file.Files;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.FileVisitResult;
import java.nio.file.Paths;
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
    private String separator = System.getProperty("line.separator");

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
                        sb.append(file.toAbsolutePath()).append(separator);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
            this.writeResultToFile(this.outFileName, sb.toString());
            System.out.printf("%s%s%s/%s%s", "File search complete",
                    "Search results stored in the file: ",
                    this.getFolderName("log.file.directory"), outFileName, separator);
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

    /**
     * Метод записывает результат поиска в файл.
     * Если файл существует, то он удаляется и создается заного.
     *
     * @param outFile фаил в который нужно записать.
     * @param result  строка с данными для записи в файл.
     */
    private void writeResultToFile(final String outFile, final String result) {
        File outFileDir = new File(this.getFolderName("log.file.directory"));
        outFileDir.mkdir();

        Path resultFile = Paths.get(outFileDir + System.getProperty("file.separator") + outFile);
        try {
            Files.deleteIfExists(resultFile);
            //Files.createFile(outFile);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (OutputStream out = Files.newOutputStream(resultFile)) {
            out.write(result.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Метод возвращает по ключу значение из файла app.properties.
     *
     * @param key ключ.
     * @return значение.
     */
    private String getFolderName(final String key) {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream fis = loader.getResourceAsStream("app.properties")) {
            settings.load(fis);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return settings.getValue(key);
    }
}
