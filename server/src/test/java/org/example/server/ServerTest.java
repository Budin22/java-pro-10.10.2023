package org.example.server;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import static org.mockito.Mockito.*;

class ServerTest {

    @Mock
    private ChatHandler mockHandler;

    private ChatServer chatServer;
    private ServerSocket mockServerSocket;

    @BeforeEach
    void setUp() throws IOException {
        MockitoAnnotations.openMocks(this);

        mockServerSocket = mock(ServerSocket.class);
        when(mockServerSocket.accept()).thenReturn(mock(Socket.class));

        chatServer = new ChatServer(8080);
    }

    @Test
    void testStart() throws IOException {
        chatServer.start();
        verify(mockServerSocket, atLeastOnce()).accept();
    }

    // Add more tests as needed

}

