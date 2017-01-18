package ru.job4j.lesson6.client;

import java.io.*;
import java.net.Socket;

/**
 *
 */
public class Client {
    private final static int port = 5000;

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("192.168.1.168", port)) {

            DataInputStream input = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

            System.out.println("Введите фразу для отправки на сервер.");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String line;
            while (true) {
                line = reader.readLine();
                out.writeUTF(line);
                line = input.readUTF();
                System.out.println(line);
                out.flush();
            }
        }
    }
}
