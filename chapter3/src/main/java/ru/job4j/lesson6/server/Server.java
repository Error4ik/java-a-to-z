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


    public void run() throws IOException {
        try (ServerSocket server = new ServerSocket(port)) {

            System.out.println("Connection to the server is expected.");
            Socket socket = server.accept();
            System.out.println("Joined: " + socket.getLocalAddress());

            in = new DataInputStream(socket.getInputStream());
            out = new DataOutputStream(socket.getOutputStream());

            out.writeUTF(showMenu());
            String line = null;

            while (!("0".equals(line))) {
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
                    out.writeUTF("Not the correct input.");
                }
            }
        }
    }


    private void getFile(final DataInputStream in, final DataOutputStream out) throws IOException {
        File file = new File(uploadDir + "/" + in.readUTF());
        long length = in.readLong();
        System.out.println("Download file length: " + length + " bytes");
        long start = System.nanoTime();
        long end;
        long time;
        try (FileOutputStream fos = new FileOutputStream(file)) {
            byte[] buffer = new byte[64 * 1024];
            int c;
            while ((c = in.read(buffer)) != -1) {
                fos.write(buffer, 0, c);
                if (file.length() == length) {
                    end = System.nanoTime();
                    time = (end - start) / 1000000000;
                    System.out.println("Download time: " + time + " seconds");
                    out.writeUTF("Ok! Download time: " + time + " seconds");
                    break;
                }
            }
        }
    }


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


    private String showMenu() {
        StringBuilder sb = new StringBuilder();
        for (String s : listAction) {
            sb.append(s).append(separator);
        }
        sb.append(separator).append("Are you here " + serverDir.getAbsolutePath()).append(separator);
        return sb.toString();
    }


    public void init() {
        listAction = new ArrayList<>();
        listAction.add("1: Show Directory");
        listAction.add("2: Download");
        listAction.add("3: Upload");
        listAction.add("4: Change Directory");
        listAction.add("0: Exit");
    }



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
