package ru.job4j.lesson6.server;


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
    private static final int port = 5000;

    /**
     *
     */
    private List<String> listAction;

    /**
     *
     */
    private boolean flag = true;

    /**
     *
     */
    private File currentDirectory;

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

    /**
     * Конструктор.
     *
     * @param path
     */
    public Server(final String path) {
        currentDirectory = new File(path);
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
                System.out.println("Получено: " + line);
                if ("1".equals(line)) {
                    out.writeUTF(showDirectory());
                } else if ("2".equals(line)) {
                    out.writeUTF("Введите название файла который нужно скачать.");
                    sendFile(in.readUTF());
                } else if ("3".equals(line)) {
                    out.writeUTF("Введите пут ьи название файла для загрузки");
                    getFile(in, out);
                } else if ("4".equals(line)) {
                    out.writeUTF("Введите имя дириктории");
                    this.changeDirectory(in.readUTF());
                    out.writeUTF(showDirectory());
                } else {
                    out.writeUTF("Вы ввели не коректные данные, попробуйте еще раз.");
                }
            }
        }
    }

    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    private void getFile(DataInputStream in, DataOutputStream out) throws IOException {
        File file = new File(currentDirectory + "/" + in.readUTF());
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
                    System.out.println("Ok");
                    end = System.nanoTime();
                    time = (end - start) / 1000000000;
                    System.out.println(time);
                    break;
                }
            }
        }
    }

    /**
     *
     * @param path
     * @throws IOException
     */
    private void sendFile(final String path) throws IOException {
        File file = new File(path);
        if (file.exists() && file.isFile()) {
            out.writeLong(file.length());
            try (FileInputStream fis = new FileInputStream(file)) {
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
     * @param directory дириктория куда нужно перейти.
     * @throws IOException Ошибка ввода вывода.
     */
    private void changeDirectory(final String directory) throws IOException {
        if ("..".equals(directory)) {
            currentDirectory = new File(currentDirectory.getParent());
        } else {
            currentDirectory = new File(currentDirectory.getPath() + "/" + directory);
        }
    }

    /**
     * Метод выводит на экран содержимое дириктории.
     *
     * @return возвращает строку в которой записано содержимое.
     */
    private String showDirectory() {
        StringBuilder sb = new StringBuilder();
        for (File sub : currentDirectory.listFiles()) {
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
        Server server = new Server("./");
        server.init();
        server.run();
    }
}
