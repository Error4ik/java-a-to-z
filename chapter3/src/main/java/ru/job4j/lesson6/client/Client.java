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
     * Константа действия.
     */
    private static final String SHOW_DIRECTORY = "1";

    /**
     * Константа действия.
     */
    private static final String DOWNLOAD = "2";

    /**
     * Константа действия.
     */
    private static final String UPLOAD = "3";

    /**
     * Константа действия.
     */
    private static final String CHANGE_DIR = "4";

    /**
     * Константа действия.
     */
    private static final String EXIT = "0";

    /**
     * Адрес порта.
     */
    private int port;

    /**
     * Хост.
     */
    private String host;

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
     * @param host Хост.
     */
    public Client(final String path, final int port, final File download, final String host) {
        this.currentDir = new File(path);
        this.port = port;
        this.downloadDir = download;
        this.host = host;
    }

    /**
     * Метод запускает сервер.
     * @throws IOException Ошибка ввода вывода.
     */
    public void runClient() throws IOException {
        try (Socket socket = new Socket(host, port)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            System.out.printf("%s", in.readUTF());

            do {
                System.out.printf("Choose actions.");
                line = reader.readLine();
                out.writeUTF(line);
                out.flush();
                if (SHOW_DIRECTORY.equals(line)) {
                    System.out.printf("%s", in.readUTF());
                }
                if (DOWNLOAD.equals(line)) {
                    System.out.printf("%s", in.readUTF());
                    this.line = reader.readLine();
                    getFile(in, out);
                }
                if (UPLOAD.equals(line)) {
                    sendFile(in, out);
                }
                if (CHANGE_DIR.equals(line)) {
                    System.out.printf("%s", in.readUTF());
                    this.line = reader.readLine();
                    out.writeUTF(this.line);
                    System.out.printf("%s", in.readUTF());
                }
            } while (!(EXIT.equals(line)));
        }
    }

    /**
     * Метод отправки файла.
     * @param in входящий поток.
     * @param out выходящий поток.
     * @throws IOException Ошибка ввода вывода.
     */
    private void sendFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        System.out.printf("Enter path and line file to send");
        System.out.printf("Eg movies/test.mp4, and documents/test.txt");
        line = reader.readLine();
        File clientFile = new File(currentDir + line);
        if (clientFile.exists() && clientFile.isFile()) {
            System.out.printf("Uploading file to server...");
            out.writeUTF(clientFile.getName());
            out.writeLong(clientFile.length());
            try (FileInputStream fis = new FileInputStream(clientFile)) {
                byte[] buffer = new byte[8192];
                int c;
                while ((c = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, c);
                }
            }
        } else {
            out.writeLong(-1L);
        }
        System.out.printf("%s", in.readUTF());
    }


    /**
     * Метод для получения файла.
     * @param in входящий поток.
     * @param out выходящий поток.
     * @throws IOException Ошибка ввода вывода.
     */
    private void getFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        out.writeUTF(line);
        long length = in.readLong();
        System.out.printf("Download file length: %s bytes", length);
        long start = System.nanoTime();
        long end;
        long time;
        if (length > -1) {
            File file = new File(downloadDir + "/" + line);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[8192];
                int c;
                while ((c = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, c);
                    if (file.length() == length) {
                        end = System.nanoTime();
                        time = (end - start) / 1000000000;
                        System.out.printf("Download time: %s seconds", time);
                        System.out.printf("The file is saved in the folder: Your root - /TEST/download");
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
        String host = settings.getValue("localhost");

        Client client = new Client("/", port, download, host);
        client.runClient();
    }
}
