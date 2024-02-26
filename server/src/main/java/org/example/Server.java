package org.example;

import org.example.server.ChatServer;

public class Server {
    public static void main(String[] args) throws RuntimeException {
        try(org.example.server.Server chatServer = new ChatServer(8080)) {
            chatServer.start();
        } catch (Exception e) {
            System.out.println("Unable to start the server: " + e.getMessage());
        }
    }
}