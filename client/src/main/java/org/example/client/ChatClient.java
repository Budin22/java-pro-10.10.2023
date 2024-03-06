package org.example.client;

import org.example.sender.ClientFileSender;
import org.example.sender.Sender;

import java.io.*;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class ChatClient implements AutoCloseable {
    private final Socket socket;
    private final Map<String, Sender> senders;

    public ChatClient(String host, int port) throws IOException {
        this.socket = new Socket(host, port);
        senders = new HashMap<>();
        senders.put("-file", new ClientFileSender());
    }

    public void connect() {
        try {
            boolean[] isOnChat = {true};
            writeMessage(isOnChat);
            readMessage(isOnChat);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void writeMessage(boolean[] isOnChat) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        new Thread(() -> {
            while (isOnChat[0]) {
                Scanner scanner = new Scanner(System.in);
                String userMessage = scanner.nextLine();

                String[] str = userMessage.split(" ");
                String senderKey = str[0];
                Sender sender = senders.get(senderKey);

                if (sender != null) {
                    try {
                        sender.action(userMessage.substring(senderKey.length() + 1), socket);
                        userMessage = "send file";
                    } catch (IOException e) {
                        userMessage = "Failed to send a file";
                    }
                }

                writer.println(userMessage);
                writer.flush();

                if (userMessage.equals("exit")) {
                    isOnChat[0] = false;
                    if (socket.isClosed()) {
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

    private void readMessage(boolean[] isOnChat) throws IOException {
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
