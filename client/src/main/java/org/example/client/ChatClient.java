package org.example.client;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient implements AutoCloseable {
    private final Socket socket;

    public ChatClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
    }

    public void connect() {
        try {
            boolean[] isOnChat = {true};
            writeMessage(socket, isOnChat);
            readMessage(socket, isOnChat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMessage(Socket socket, boolean[] isOnChat) throws IOException {
        OutputStream outputStream = socket.getOutputStream();

        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        new Thread(() -> {
            while (isOnChat[0]) {
                Scanner scanner = new Scanner(System.in);
                String userMessage = scanner.nextLine();

                writer.println(userMessage);
                writer.flush();
                if (userMessage.equals("exit")) {
                    isOnChat[0] = false;
                    if(socket.isClosed()) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }

                }
            }
        }
        ).start();
    }

    private void readMessage(Socket socket, boolean[] isOnChat) throws IOException {
        final InputStream inputStream = socket.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

        while (isOnChat[0]) {
            String s = reader.readLine();
            if (s != null) System.out.println(s);
        }
    }

    @Override
    public void close() throws Exception {
        socket.close();
    }
}
