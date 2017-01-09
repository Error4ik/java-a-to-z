package ru.job4j.lesson3;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * Класс для сортировки строк в файле.
 */
public class SortFile {

    /**
     * Переход на новую строку.
     */
    private final String separator = System.getProperty("line.separator");

    /**
     * Метод для сортировки строк в файле по длинне.
     *
     * @param inFile  фаил в котором не отсортированные строкие.
     * @param outFile фаил для записи отсортированных строк.
     * @throws IOException Ошибка ввода вывода.
     */
    public void sortByLongLines(final File inFile, File outFile) throws IOException {

        try (RandomAccessFile rafOriginFile = new RandomAccessFile(inFile, "r");
             RandomAccessFile rafOutFile = new RandomAccessFile(outFile, "rw")) {

            String line = rafOriginFile.readLine();
            rafOutFile.writeBytes(String.format("%s", line));

            while ((line = rafOriginFile.readLine()) != null) {
                rafOutFile.writeBytes(String.format("%s%s", separator, line));
            }
        }

        boolean flag = true;

        while (flag) {
            File firstTmpFile = new File("firstTmpFile.txt");
            File secondTmpFile = new File("secondTmpFile.txt");

            splitFile(outFile, firstTmpFile, secondTmpFile);

            outFile.delete();
            outFile = new File(outFile.getPath());

            flag = mergeFile(outFile, firstTmpFile, secondTmpFile);

            firstTmpFile.delete();
            secondTmpFile.delete();
        }
    }

    /**
     * Метод для разделения входного файла на 2 подфайла.
     *
     * @param inFile        Основной фаил.
     * @param firstTmpFile  Первый подфаил.
     * @param secondTmpFile Второй подфаил.
     * @throws IOException Ошибка ввода вывода.
     */
    private void splitFile(File inFile, File firstTmpFile, File secondTmpFile) throws IOException {
        try (RandomAccessFile rafOriginFile = new RandomAccessFile(inFile, "r");
             RandomAccessFile rafFirstTmp = new RandomAccessFile(firstTmpFile, "rw");
             RandomAccessFile rafSecondTmp = new RandomAccessFile(secondTmpFile, "rw")) {

            String firstLine = rafOriginFile.readLine();
            String secondLine;
            int count = 0;
            rafFirstTmp.writeBytes(firstLine);

            while ((secondLine = rafOriginFile.readLine()) != null) {
                if (secondLine.length() < firstLine.length()) {
                    count++;
                    if (count == 1) {
                        rafSecondTmp.writeBytes(secondLine);
                        firstLine = secondLine;
                    } else {
                        rafSecondTmp.writeBytes(String.format("%s%s", separator, secondLine));
                        firstLine = secondLine;
                    }
                } else {
                    rafFirstTmp.writeBytes(String.format("%s%s", separator, secondLine));
                    firstLine = secondLine;
                }
            }
        }
    }

    /**
     * Метод слияния двух файлов в один основной.
     *
     * @param inFile        Основной фаил в который будут записываться отсортированные строки из двух подфайлов.
     * @param firstTmpFile  Первый подфаил.
     * @param secondTmpFile Второй подфаил.
     * @return Если во втором подфайле не оказалось строк, то сортировка закончена и метод вернет false.
     * @throws IOException Ошибка ввода вывода.
     */
    private boolean mergeFile(File inFile, File firstTmpFile, File secondTmpFile) throws IOException {
        boolean flag = true;
        try (RandomAccessFile rafOutFile = new RandomAccessFile(inFile, "rw");
             RandomAccessFile rafFirstTmp = new RandomAccessFile(firstTmpFile, "r");
             RandomAccessFile rafSecondTmp = new RandomAccessFile(secondTmpFile, "r")) {

            String firstLine = rafFirstTmp.readLine();
            String secondLine = rafSecondTmp.readLine();
            int count = 0;

            if (secondLine == null) {
                flag = false;
            }

            while (firstLine != null && secondLine != null) {
                count++;
                if (firstLine.length() < secondLine.length()) {
                    if (count == 1) {
                        rafOutFile.writeBytes(firstLine);
                        firstLine = rafFirstTmp.readLine();
                    } else {
                        rafOutFile.writeBytes(String.format("%s%s", separator, firstLine));
                        firstLine = rafFirstTmp.readLine();
                    }
                } else {
                    if (count == 1) {
                        rafOutFile.writeBytes(secondLine);
                        secondLine = rafSecondTmp.readLine();
                    } else {
                        rafOutFile.writeBytes(String.format("%s%s", separator, secondLine));
                        secondLine = rafSecondTmp.readLine();
                    }
                }
            }

            while (firstLine != null) {
                rafOutFile.writeBytes(String.format("%s%s", separator, firstLine));
                firstLine = rafFirstTmp.readLine();
            }
        }
        return flag;
    }
}