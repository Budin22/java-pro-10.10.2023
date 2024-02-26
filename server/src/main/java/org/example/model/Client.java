package org.example.model;

import java.net.Socket;
import java.time.LocalDateTime;

public class Client {
    private String name;
    private LocalDateTime connectedTime;
    private Socket socket;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDateTime getConnectedTime() {
        return connectedTime;
    }

    public void setConnectedTime(LocalDateTime connectedTime) {
        this.connectedTime = connectedTime;
    }

    public Socket getSocket() {
        return socket;
    }

    public void setSocket(Socket socket) {
        this.socket = socket;
    }
}
