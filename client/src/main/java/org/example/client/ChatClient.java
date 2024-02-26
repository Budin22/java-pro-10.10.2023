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
            final InputStream inputStream = socket.getInputStream();
            final OutputStream outputStream = socket.getOutputStream();

            PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            boolean[] isOnChat = {true};

            Thread receiveMessage = new Thread(() ->
            {
                while (isOnChat[0]) {
                    Scanner scanner = new Scanner(System.in);
                    String userMessage = scanner.nextLine();
                    if (userMessage.equals("STOP")) {
                        isOnChat[0] = false;
                    }

                    writer.println(userMessage);
                    writer.flush();
                }
            }
            );
            receiveMessage.start();

            while (isOnChat[0]) {
                String s = reader.readLine();
                System.out.println(s);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }


    @Override
    public void close() throws Exception {
        socket.close();
    }
}
