package ru.job4j.lesson6.server;

import ru.job4j.lesson6.settings.Settings;

import java.io.IOException;
import java.io.File;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.Socket;
import java.net.ServerSocket;

/**
 * Класс сервера.
 */
public class Server {

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
     * Порт.
     */
    private int port;

    /**
     * Корневая дириктория.
     */
    private File serverDir;

    /**
     * Дириктория куда помещаются загруженные на сервер файлы.
     */
    private File uploadDir;

    /**
     * Разделитель.
     */
    private String separator = System.getProperty("line.separator");


    /**
     * Конструктор.
     *
     * @param path   Путь к корневому каталогу.
     * @param port   порт.
     * @param upload папка для загрузок на сервер.
     */
    public Server(final String path, final int port, final File upload) {
        this.serverDir = new File(path);
        this.port = port;
        this.uploadDir = upload;
    }

    /**
     * Метод запуска клиента.
     *
     * @throws IOException ошибка ввода вывода.
     */
    public void run() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.printf("Connection to the server is expected.");
            Socket socket = server.accept();
            System.out.printf("Joined: %s", socket.getLocalAddress());

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(showMenu());
            String line = null;

            while (!(EXIT.equals(line))) {
                line = in.readUTF();
                System.out.printf("Получен запрос: %s", line);
                if (SHOW_DIRECTORY.equals(line)) {
                    out.writeUTF(showDirectory());
                } else if (DOWNLOAD.equals(line)) {
                    this.sendFile(in, out);
                } else if (UPLOAD.equals(line)) {
                    this.getFile(in, out);
                } else if (CHANGE_DIR.equals(line)) {
                    this.changeDirectory(in, out);
                } else {
                    out.writeUTF("Not the correct input.");
                }
            }
        }
    }

    /**
     * Метод получения файла.
     *
     * @param in  входной поток.
     * @param out выходной поток.
     * @throws IOException ошибка ввода вывода.
     */
    private void getFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        File file = new File(uploadDir + "/" + in.readUTF());
        long length = in.readLong();
        System.out.printf("Download file length: %s bytes", length);
        long start = System.nanoTime();
        long end;
        long time;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[8192];
            int c;
            while ((c = in.read(buffer)) != -1) {
                fos.write(buffer, 0, c);
                if (file.length() == length) {
                    end = System.nanoTime();
                    time = (end - start) / 1000000000;
                    System.out.printf("Download time: %s seconds", time);
                    out.writeUTF("Ok! Download time: " + time + " seconds");
                    break;
                }
            }
        }
    }

    /**
     * Метод отправки файла.
     *
     * @param in  входной поток.
     * @param out выходной поток.
     * @throws IOException ошибка ввода вывода.
     */
    private void sendFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        out.writeUTF("Enter name download file.");
        String line = in.readUTF();
        File serverFile;
        if (serverDir.getParent() == null) {
            serverFile = new File(serverDir + line);
        } else {
            serverFile = new File(serverDir + "/" + line);
        }
        if (serverFile.exists() && serverFile.isFile()) {
            out.writeLong(serverFile.length());
            try (FileInputStream fis = new FileInputStream(serverFile)) {
                byte[] buffer = new byte[8192];
                int c;
                while ((c = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, c);
                }
            }
        } else {
            out.writeLong(-1L);
        }
    }

    /**
     * Метод смены дириктории.
     *
     * @param in  входной поток.
     * @param out выходной поток.
     * @throws IOException ошибка ввода вывода.
     */
    private void changeDirectory(final DataInputStream in, final DataOutputStream out) throws IOException {
        out.writeUTF("Enter directory name.");
        String line = in.readUTF();
        if ("..".equals(line)) {
            if (serverDir.getParent() == null) {
                out.writeUTF("You in the root catalog.");
            } else {
                serverDir = new File(serverDir.getParent());
            }
        } else {
            if (serverDir.getParent() == null) {
                File tmp = new File(serverDir.getPath() + line);
                if (tmp.exists() && tmp.isDirectory()) {
                    serverDir = new File(serverDir.getPath() + line);
                } else {
                    out.writeUTF("Invalid name directory");
                }
            } else {
                File tmp = new File(serverDir.getPath() + "/" + line);
                if (tmp.exists() && tmp.isDirectory()) {
                    serverDir = new File(serverDir.getPath() + "/" + line);
                } else {
                    out.writeUTF("Invalid name directory");
                }
            }
        }
        out.writeUTF(separator + "Are you here " + serverDir.getAbsolutePath());
    }

    /**
     * Метод получает список дирикторий и записывает их в строку.
     *
     * @return строку.
     */
    private String showDirectory() {
        StringBuilder sb = new StringBuilder();
        for (File sub : serverDir.listFiles()) {
            if (!sub.isHidden()) {
                if (sub.isDirectory()) {
                    sb.append("/");
                }
                sb.append(sub.getName()).append(separator);
            }
        }
        return sb.toString();
    }

    /**
     * Метод формирует стрку с меню.
     * @return строку меню.
     */
    private String showMenu() {
        return new StringBuilder()
                .append("1: Show Directory").append(separator)
                .append("2: Download").append(separator)
                .append("3: Upload").append(separator)
                .append("4: Change Directory").append(separator)
                .append("0: Exit").append(separator)
                .append("Are you here ")
                .append(serverDir.getAbsolutePath())
                .append(separator)
                .toString();
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

        File upload = new File(settings.getValue("server"));
        upload.mkdirs();
        int port = Integer.parseInt(settings.getValue("port"));

        Server ser = new Server("/", port, upload);
        ser.run();
    }
}
