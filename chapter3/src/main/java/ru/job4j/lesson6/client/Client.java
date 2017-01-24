package ru.job4j.lesson6.client;

import ru.job4j.lesson6.settings.Settings;

import java.io.IOException;
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * Класс клиента.
 */
public class Client {

    /**
     * Адрес порта.
     */
    private int port;

    /**
     * Хранит ввод пользователя.
     */
    private String line;

    /**
     * Читает ввод пользователя.
     */
    private BufferedReader reader;

    /**
     * Текущая дириктория в начале установлена корневая.
     */
    private File currentDir;

    /**
     * Дириктория куда будут сохранятсся скаченные файлы.
     */
    private File downloadDir;

    /**
     * Конструктор.
     * @param path корневой путь.
     * @param port порт.
     * @param download дириктория для файлов.
     */
    public Client(final String path, final int port, final File download) {
        this.currentDir = new File(path);
        this.port = port;
        this.downloadDir = download;
    }

    /**
     * Метод запускает сервер.
     * @throws IOException Ошибка ввода вывода.
     */
    public void runClient() throws IOException {
        try (Socket socket = new Socket("localhost", port)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            System.out.println(in.readUTF());

            do {
                System.out.println("Choose actions.");
                line = reader.readLine();
                out.writeUTF(line);
                out.flush();
                if ("1".equals(line)) {
                    System.out.println(in.readUTF());
                }
                if ("2".equals(line)) {
                    System.out.println(in.readUTF());
                    this.line = reader.readLine();
                    getFile(in, out);
                }
                if ("3".equals(line)) {
                    sendFile(in, out);
                }
                if ("4".equals(line)) {
                    System.out.println(in.readUTF());
                    this.line = reader.readLine();
                    out.writeUTF(this.line);
                    System.out.println(in.readUTF());
                }
            } while (!("0".equals(line)));
        }
    }

    /**
     * Метод отправки файла.
     * @param in входящий поток.
     * @param out выходящий поток.
     * @throws IOException Ошибка ввода вывода.
     */
    private void sendFile(DataInputStream in, DataOutputStream out) throws IOException {
        System.out.println("Enter path and line file to send");
        System.out.println("Eg movies/test.mp4, and documents/test.txt");
        line = reader.readLine();
        File clientFile = new File(currentDir + line);
        if (clientFile.exists() && clientFile.isFile()) {
            System.out.println("Uploading file to server...");
            out.writeUTF(clientFile.getName());
            out.writeLong(clientFile.length());
            try (FileInputStream fis = new FileInputStream(clientFile)) {
                byte[] buffer = new byte[64 * 1024];
                int c;
                while ((c = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, c);
                }
            }
        } else {
            out.writeLong(-1L);
        }
        System.out.println(in.readUTF());
    }


    /**
     * Метод для получения файла.
     * @param in входящий поток.
     * @param out выходящий поток.
     * @throws IOException Ошибка ввода вывода.
     */
    private void getFile(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF(line);
        long length = in.readLong();
        System.out.println("Download file length: " + length + " bytes");
        long start = System.nanoTime();
        long end;
        long time;
        if (length > -1) {
            File file = new File(downloadDir + "/" + line);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[64 * 1024];
                int c;
                while ((c = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, c);
                    if (file.length() == length) {
                        end = System.nanoTime();
                        time = (end - start) / 1000000000;
                        System.out.println("Download time: " + time + " seconds");
                        System.out.println("The file is saved in the folder: Your root - /TEST/download");
                        break;
                    }
                }
            }
        }
    }


    /**
     * Точка входа.
     * @param args аргументы.
     * @throws IOException ошибка ввода вывода.
     */
    public static void main(String[] args) throws IOException {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream fis = loader.getResourceAsStream("app.properties")) {
            settings.load(fis);
        }
        File download = new File(settings.getValue("client"));
        download.mkdirs();
        int port = Integer.parseInt(settings.getValue("port"));

        Client client = new Client("/", port, download);
        client.runClient();
    }
}
