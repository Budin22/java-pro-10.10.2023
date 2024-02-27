package org.example.server;

import org.example.util.MyLocalDateTime;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.util.Objects;

public class DefaultChatClient implements ChatClient {
    private final Socket socket;
    private final ChatHandler handler;
    private BufferedReader reader;
    private PrintWriter writer;
    private String name;
    private LocalDateTime connectedTime;

    public DefaultChatClient(Socket socket, ChatHandler handler) {
        this.socket = socket;
        this.handler = handler;

        new Thread(() -> {
            try (socket) {
                InputStream inputStream = socket.getInputStream();
                OutputStream outputStream = socket.getOutputStream();

                reader = new BufferedReader(new InputStreamReader(inputStream));
                writer = new PrintWriter(new OutputStreamWriter(outputStream));

                while (name == null || name.isEmpty()) {
                    writer.println("Welcome to the chat. Enter your name:");
                    writer.flush();
                    name = reader.readLine();
                    connectedTime = MyLocalDateTime.getLocalDateTime();
                }

                this.handler.onConnect(this);

                while (!socket.isClosed()) {
                    final String message = reader.readLine();
                    if (message != null && !message.equals("exit")) {
                        handler.onMessage(this, message);
                    } else break;
                }

            } catch (IOException e) {
                handler.onError(this, e);
            } finally {
                handler.onDisconnect(this);
            }
        }).start();
    }

    @Override
    public void sendMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public LocalDateTime getConnectedTime() {
        return connectedTime;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        DefaultChatClient that = (DefaultChatClient) object;
        return Objects.equals(socket, that.socket) && Objects.equals(name, that.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(socket, name);
    }
}
