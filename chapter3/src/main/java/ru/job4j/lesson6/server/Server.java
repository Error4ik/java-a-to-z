package ru.job4j.lesson6.server;


import ru.job4j.lesson6.settings.Settings;

import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class Server {

    /**
     *
     */
    private int port;

    /**
     *
     */
    private List<String> listAction;

    /**
     *
     */
    private File serverDir;

    /**
     *
     */
    private File uploadDir;

    /**
     *
     */
    private String separator = System.getProperty("line.separator");

    /**
     *
     */
    private DataInputStream in;

    /**
     *
     */
    private DataOutputStream out;


    public Server(final String server, final int port, final File upload) {
        this.serverDir = new File(server);
        this.port = port;
        this.uploadDir = upload;
    }

    /**
     * Метод запуска сервера.
     *
     * @throws IOException ошибка ввода вывода.
     */
    public void run() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Ожидается подключение к серверу.");
            Socket socket = server.accept();
            System.out.println("Присоеденился: " + socket.getLocalAddress());

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(showMenu());
            String line = null;

            while (!("exit".equals(line))) {
                line = in.readUTF();
                System.out.println("Получен запрос: " + line);
                if ("1".equals(line)) {
                    out.writeUTF(showDirectory());
                } else if ("2".equals(line)) {
                    this.sendFile(in, out);
                } else if ("3".equals(line)) {
                    this.getFile(in, out);
                } else if ("4".equals(line)) {
                    this.changeDirectory(in, out);
                } else {
                    out.writeUTF("Вы ввели не коректные данные, попробуйте еще раз.");
                }
            }
        }
    }

    /**
     * @param in
     * @param out
     * @throws IOException
     */
    private void getFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        File file = new File(uploadDir + "/" + in.readUTF());
        long length = in.readLong();
        System.out.println("Загружается файл длинной: " + length);
        long start = System.nanoTime();
        long end;
        long time;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[64 * 1024];
            int c;
            while ((c = in.read(buffer)) != -1) {
                fos.write(buffer, 0, c);
                if (file.length() == length) {
                    out.writeUTF("Ok");
                    end = System.nanoTime();
                    time = (end - start) / 1000000000;
                    System.out.println("Время загрузки: " + time + " сек.");
                    break;
                }
            }
        }
    }

    /**
     * @throws IOException
     */
    private void sendFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        out.writeUTF("Введите название файла который нужно скачать.");
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
                byte[] buffer = new byte[64 * 1024];
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
     * Метод для перемещения по дирикториям.
     *
     * @throws IOException Ошибка ввода вывода.
     */
    private void changeDirectory(final DataInputStream in, final DataOutputStream out) throws IOException {
        out.writeUTF("Введите имя дириктории");
        String line = in.readUTF();
        if ("..".equals(line)) {
            if (serverDir.getParent() == null) {
                out.writeUTF("Вы находитесь в корневом каталоге.");
            } else {
                serverDir = new File(serverDir.getParent());
                out.writeUTF(serverDir.getAbsolutePath());
            }
        } else {
            if (serverDir.getParent() == null) {
                File tmp = new File(serverDir.getPath() + line);
                if (tmp.exists() && tmp.isDirectory()) {
                    serverDir = new File(serverDir.getPath() + line);
                    out.writeUTF(serverDir.getAbsolutePath());
                } else {
                    out.writeUTF("Не верная дириктория");
                }
            } else {
                File tmp = new File(serverDir.getPath() + "/" + line);
                if (tmp.exists() && tmp.isDirectory()) {
                    serverDir = new File(serverDir.getPath() + "/" + line);
                    out.writeUTF(serverDir.getAbsolutePath());
                } else {
                    out.writeUTF("Не верная дириктория");
                }
            }
        }
    }

    /**
     * Метод выводит на экран содержимое дириктории.
     *
     * @return возвращает строку в которой записано содержимое.
     */
    private String showDirectory() {
        StringBuilder sb = new StringBuilder();
        for (File sub : serverDir.listFiles()) {
            if (sub.isDirectory()) {
                sb.append("/");
            }
            sb.append(sub.getName()).append(separator);
        }
        return sb.toString();
    }

    /**
     * Выводит на экран меню.
     */
    private String showMenu() {
        StringBuilder sb = new StringBuilder();
        for (String s : listAction) {
            sb.append(s).append(separator);
        }
        return sb.toString();
    }

    /**
     * Заполняет лист действиями.
     */
    public void init() {
        listAction = new ArrayList<>();
        listAction.add("1: Show Directory");
        listAction.add("2: Download");
        listAction.add("3: Upload");
        listAction.add("4: Change Directory");
    }


    /**
     * Точка входа.
     *
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
        ser.init();
        ser.run();
    }
}
