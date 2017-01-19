package ru.job4j.lesson6.client;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 *
 */
public class Client {
    private final static int port = 5000;

    private static String name;



    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("192.168.1.168", port)) {

            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Введите команду для отправки на сервер.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;
            System.out.println(in.readUTF());
            while (!("exit".equals(line = reader.readLine()))) {
                out.writeUTF(line);
                out.flush();
                if ("download".equals(line)) {
                    System.out.println(in.readUTF());
                    name = reader.readLine();
                    poluchitFile(in, out);
                }

            }
        }
    }

    private static void poluchitFile(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF(name);
        long length = in.readLong();
        System.out.println(length);
        while (length >= 0) {
            try (FileOutputStream outputStream = new FileOutputStream(new File("./chapter1/" + "/" + name))) {
                byte[] buffer = new byte[(int) length];
                int c;
                while ((c = in.read(buffer)) != -1) {
                    outputStream.write(buffer, 0, c);
                }
            }
            File file = new File("./chapter1/" + "/" + name);
             if (file.length() == length) {
                 System.out.println("ok");
             } else {
                 System.out.println("not ok");
             }
        }

    }
}
