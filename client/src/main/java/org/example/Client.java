package org.example;

import org.example.client.ChatClient;

import java.io.IOException;

public class Client {
    public static void main(String[] args) {
        try (ChatClient client = new ChatClient("127.0.0.1", 8080)) {
            client.connect();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}