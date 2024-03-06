package org.example.server;

import java.net.Socket;
import java.time.LocalDateTime;

public interface ChatClient {
    void sendMessage(String message);
    String getName();
    LocalDateTime getConnectedTime();
    Socket getSocket();
}
