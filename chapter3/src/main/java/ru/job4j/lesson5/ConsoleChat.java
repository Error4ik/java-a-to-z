package ru.job4j.lesson5;

import java.io.IOException;
import java.io.File;
import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.RandomAccessFile;
import java.io.OutputStreamWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.Random;

/**
 * Консольный чат.
 */
public class ConsoleChat {

    /**
     * Стоп останавливает вывод ответных сообщений.
     */
    private final String stop = "стоп";

    /**
     * Возобнавляет вывод ответных сообщений.
     */
    private final String resume = "продолжить";

    /**
     * Завершает основный цикл программы.
     */
    private final String exit = "закончить";

    /**
     * Разделитель для строк.
     */
    private String separator = System.getProperty("line.separator");

    /**
     * Флаг включает или отключает ответные сообщения.
     */
    private boolean flag = true;

    /**
     * Файл в который будет записан лог сообщений.
     */
    private File log;

    /**
     * Файл с ответами.
     */
    private File answer;

    /**
     * Ввод для консоли.
     */
    private Input input;

    /**
     * Конструктор.
     *
     * @param answer файл с ответами для чата.
     * @param out    файл в который нужно записать лог разговора.
     * @param input  ввод с консоли.
     */
    public ConsoleChat(final File answer, final File out, final Input input) {
        this.answer = answer;
        this.log = out;
        this.input = input;
    }

    /**
     * Запускает консольный чат.
     *
     * @throws IOException ошибка ввода вывода.
     */
    public void runChat() throws IOException {
        try (RandomAccessFile reader = new RandomAccessFile(answer, "r");
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(log)))) {

            System.out.println("Отключить бота - стоп.");
            System.out.println("Включить бота - продолжить.");
            System.out.println("Выйти из программы - закончить.");
            System.out.println("Введите любую фразу.");

            String human = input.getInput().toLowerCase();

            while (!exit.equals(human)) {
                writer.write(String.format("%s%s", human, separator));

                if (stop.equals(human.toLowerCase())) {
                    flag = false;
                }
                if (resume.equals(human.toLowerCase())) {
                    flag = true;
                }

                if (flag) {
                    reader.seek(new Random().nextInt((int) reader.length()));
                    reader.readLine();
                    String chatBot = reader.readLine();
                    if (chatBot == null) {
                        reader.seek(0);
                        chatBot = reader.readLine();
                    }

                    chatBot = new String(chatBot.getBytes("ISO-8859-1"), "UTF-8");

                    System.out.println(chatBot);
                    writer.write(String.format("%s%s", chatBot, separator));
                }
                human = input.getInput().toLowerCase();
            }
            writer.write("Закончить");
        }
    }

    /**
     * Основной метод запуска.
     *
     * @param args аргументы.
     * @throws IOException ошибка ввода вывода.
     */
    public static void main(String[] args) throws IOException {
        Properties prop = new Properties();
        prop.load(new InputStreamReader(new FileInputStream(new File("chapter3/app.properties"))));
        ConsoleInput input = new ConsoleInput();
        File in = new File(prop.getProperty("pathIn"));
        File out = new File(prop.getProperty("pathOut"));
        ConsoleChat consoleChat = new ConsoleChat(in, out, input);
        consoleChat.runChat();
    }
}