package ru.job4j.lesson6.client;

import java.io.*;
import java.net.Socket;

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

            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

            String line;

            do {
                System.out.println(in.readUTF());
                System.out.println("Выберите действие.");
                line = reader.readLine();
                out.writeUTF(line);
                out.flush();

                if ("2".equals(line)) {
                    System.out.println(in.readUTF());
                    name = reader.readLine();
                    getFile(in, out);
                }

            } while (!("exit".equals(line)));
        }
    }

    private static void getFile(DataInputStream in, DataOutputStream out) throws IOException {
        out.writeUTF(name);
        long length = in.readLong();
        System.out.println(length);
        if (length > -1) {
            File file = new File("./chapter1/" + "/" + name);
            try (FileOutputStream outputStream = new FileOutputStream(new File("./chapter1/" + "/" + name))) {
                byte[] buffer = new byte[8 * 1024];
                int c = 0;
                while ((c = in.read(buffer)) > 0) {
                    outputStream.write(buffer, 0, c);
                    if (file.length() == length) {
                        break;
                    }
                }
            }

            if (file.length() == length) {
                System.out.println("Ok");
            } else {
                System.out.println("Not Ok");
            }
        }
    }
}
