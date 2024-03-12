package org.example.server;

import org.example.sender.FileSender;
import org.example.sender.Sender;
import org.example.util.MyLocalDateTime;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer implements Server, ChatHandler, AutoCloseable {
    private final ServerSocket serverSocket;
    private final List<ChatClient> chatClientList = new ArrayList<>();
    private final Map<String, Sender> senders;

    public ChatServer(int port, Map<String, Sender> senders) throws IOException {
        serverSocket = new ServerSocket(port);
        this.senders = senders;
    }

    public ChatServer(int port) throws IOException {
        serverSocket = new ServerSocket(port);
        senders = new HashMap<>();
        senders.put("-file", new FileSender());
    }

    @Override
    public void start() throws IOException {
        System.out.println("server start");
        while (!serverSocket.isClosed()) {
            Socket socket = serverSocket.accept();
            new DefaultChatClient(socket, this);
        }
    }

    @Override
    public void onConnect(ChatClient client) {
        chatClientList.add(client);
        chatClientList.forEach(c -> c.sendMessage("[SERVER] " + client.getName() + " connected to server: " + MyLocalDateTime.parseLocalDateTimeToString(client.getConnectedTime())));
    }

    @Override
    public void onMessage(ChatClient client, String message) {
        try{
            String strategy = message.split(" ")[0];
            Sender sender = senders.get(strategy);
            if (sender != null) {
                String currentMessage = message.substring(strategy.length() + 1);
                sender.action(currentMessage);
            } else {
                for (ChatClient conn :
                        chatClientList) {
                    conn.sendMessage("[" + client.getName() + "]: " + message);
                }
            }
        } catch (Exception e){
            client.sendMessage("[SERVER]: error - " + e.getMessage());
        }
    }

    @Override
    public void onDisconnect(ChatClient client) {
        chatClientList.remove(client);
        chatClientList.forEach(c -> c.sendMessage("[SERVER] " + client.getName() + " left the chat " + MyLocalDateTime.getLocalDateTimeInString()));
    }

    @Override
    public void onError(ChatClient connection, Exception e) {
        connection.sendMessage("Error occurred: " + e.getMessage());
    }

    @Override
    public void close() throws Exception {
        if (!serverSocket.isClosed()) {
            serverSocket.close();
        }
    }
}
