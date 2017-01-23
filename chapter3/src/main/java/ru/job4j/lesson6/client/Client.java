package ru.job4j.lesson6.client;

import ru.job4j.lesson6.settings.Settings;

import java.io.*;
import java.net.Socket;

/**
 *
 */
public class Client {

    /**
     *
     */
    private String separator = System.getProperty("line.separator");

    /**
     *
     */
    private int port;

    /**
     *
     */
    private String name;

    /**
     *
     */
    private BufferedReader reader;

    /**
     *
     */
    private File currentDir;

    /**
     *
     */
    private File downloadDir;

    /**
     * Конструктор
     */
    public Client(final String path, final int port, final File download) {
        this.currentDir = new File(path);
        this.port = port;
        this.downloadDir = download;
    }
    /**
     *
     * @throws IOException
     */
    public void runClient() throws IOException {
        try (Socket socket = new Socket("localhost", port)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            System.out.println(in.readUTF());

            do {
                System.out.println("Выберите действие.");
                line = reader.readLine();
                out.writeUTF(line);
                out.flush();
                if ("1".equals(line)) {
                    System.out.println(in.readUTF());
                }
                if ("2".equals(line)) {
                    System.out.println(in.readUTF());
                    name = reader.readLine();
                    getFile(in, out);
                }
                if ("3".equals(line)) {
                    sendFile(in, out);
                }
                if ("4".equals(line)) {
                    System.out.println(in.readUTF());
                    name = reader.readLine();
                    out.writeUTF(name);
                    System.out.println(in.readUTF());
                }
            } while (!("exit".equals(line)));
        }
    }

    /**
     *
     * @param in
     * @param out
     * @throws IOException
     */
    private void sendFile(DataInputStream in, DataOutputStream out) throws IOException {
        System.out.println("Введите имя файла для отправки.");
        name = reader.readLine();
        File clientFile = new File(currentDir + name);
        if (clientFile.exists() && clientFile.isFile()) {
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
     *
     * @param in
     * @param out
     * @throws IOException
     */
    private void getFile(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF(name);
        long length = in.readLong();
        System.out.println(length);
        long start = System.nanoTime();
        long end;
        long time;
        if (length > -1) {
            File file = new File(downloadDir + "/" + name);
            try (FileOutputStream fos = new FileOutputStream(file)) {
                byte[] buffer = new byte[64 * 1024];
                int c;
                while ((c = in.read(buffer)) != -1) {
                    fos.write(buffer, 0, c);
                    if (file.length() == length) {
                        System.out.println("Ok");
                        end = System.nanoTime();
                        time = (end - start) / 1000000000;
                        System.out.println("Время загрузки: " + time + " сек.");
                        break;
                    }
                }
            }
        }
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Settings settings = new Settings();
        ClassLoader loader = Settings.class.getClassLoader();
        try (InputStream fis = loader.getResourceAsStream("app.properties")) {
            settings.load(fis);
        }
        File downlod = new File(settings.getValue("client"));
        downlod.mkdirs();
        int port = Integer.parseInt(settings.getValue("port"));

        Client client = new Client("/", port, downlod);
        client.runClient();
    }
}
