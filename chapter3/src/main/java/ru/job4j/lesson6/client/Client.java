package ru.job4j.lesson6.client;

import java.io.*;
import java.net.Socket;

/**
 *
 */
public class Client {

    /**
     *
     */
    private final int port = 5000;

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
     * Конструктор
     * @param path
     */
    public Client(final String path) {
        currentDir = new File(path);
    }
    /**
     *
     * @throws IOException
     */
    public void runClient() throws IOException {
        try (Socket socket = new Socket("192.168.1.168", port)) {

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
                    getFile(in, out);
                }
                if ("3".equals(line)) {
                    sendFile(in, out);
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
        System.out.println("Введите путь и имя файла для отправки.");
        System.out.println("Например: download/image/5.jpg");
        name = reader.readLine();
        System.out.println(name);
        File file = new File(currentDir + name);
        //if (file.exists() && file.isFile()) {
            out.writeUTF(file.getName());
            out.writeLong(file.length());
            try (FileInputStream fis = new FileInputStream(file)) {
                byte[] buffer = new byte[64 * 1024];
                int c;
                while ((c = fis.read(buffer)) != -1) {
                    out.write(buffer, 0, c);
                }
            }
//        } else {
//            out.writeLong(-1L);
//        }
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
            File file = new File("c:/download_test" + "/" + name);
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
    }

    /**
     *
     * @param args
     * @throws IOException
     */
    public static void main(String[] args) throws IOException {
        Client client = new Client("/");
        client.runClient();
    }
}
