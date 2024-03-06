package org.example.sender;

import java.io.IOException;
import java.net.Socket;

public interface Sender {
    void action(String message, Socket socket) throws IOException;
}
