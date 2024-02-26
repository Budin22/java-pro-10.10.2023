package org.example.server;

import java.time.LocalDateTime;

public interface ChatClient {
    void sendMessage(String message);
    String getName();
    LocalDateTime getConnectedTime();
}
