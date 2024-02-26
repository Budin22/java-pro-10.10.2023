package org.example.server;

public interface ChatHandler {
    void onConnect(ChatClient client);
    void onMessage(ChatClient client, String message);
    void onDisconnect(ChatClient client);
    void onError(ChatClient client, Exception e);
}
