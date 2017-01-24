package ru.job4j.lesson5;

import org.junit.Test;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.File;
import java.io.BufferedReader;
import java.io.OutputStreamWriter;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 *
 */
public class ConsoleChatTest {

    /**
     * Тест консьльного чата.
     *
     * @throws IOException Ошибка ввода вывода.
     */
    @Test
    public void runChat() throws IOException {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream fis = loader.getResourceAsStream("app.properties")) {
            settings.load(fis);
        }
        final String[] test = new String[]{"Hi", "стоп", "Ответь", "Продолжить", "Закончить"};
        final StubInput stubInput = new StubInput(test);
        String outFile = "../" + settings.getValue("pathOut");
        final File in = File.createTempFile("tmp", "txt");
        final File out = new File(outFile);

        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(in), StandardCharsets.UTF_8))) {
            writer.write("Привет");
        }

        final ConsoleChat consoleChat = new ConsoleChat(in, out, stubInput);
        consoleChat.runChat();
        final List<String> expected = Arrays.asList("hi", "Привет", "стоп", "ответь", "продолжить", "Привет", "Закончить");
        final List<String> actual = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(out), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                actual.add(line);
            }
        }
        assertThat(actual, is(expected));
    }

    /**
     * Тест броска исключения если фаил не найден.
     *
     * @throws IOException ошибка ввода вывода.
     */
    @Test(expected = IOException.class)
    public void chatException() throws IOException {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream fis = loader.getResourceAsStream("app.properties")) {
            settings.load(fis);
        }
        final String[] test = new String[]{"Hi", "стоп", "Ответь", "Продолжить", "Закончить"};
        final StubInput stubInput = new StubInput(test);
        final File in = new File("empty");
        final File out = new File(settings.getValue("pathOut"));
        final ConsoleChat consoleChat = new ConsoleChat(in, out, stubInput);
        consoleChat.runChat();
    }
}