package org.example.server;

import org.example.sender.Sender;
import org.example.util.MyLocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;

public class ChatServerTest {
    @Mock
    private ServerSocket serverSocket;
    @Mock
    private Socket socket;
    @Mock
    private Sender sender;
    private ChatServer chatServer;

    @BeforeEach
    public void setUp() throws IOException {
        MockitoAnnotations.initMocks(this);
        when(serverSocket.accept()).thenReturn(socket);
        Map<String, Sender> senders = new HashMap<>();
        senders.put("mock", sender);
        chatServer = new ChatServer(8080, senders);
    }

    @Test
    public void testStart() throws IOException {
        chatServer.start();
        verify(serverSocket, times(1)).accept();
    }

    @Test
    public void testOnMessage() {
        ChatClient chatClient = mock(ChatClient.class);
        when(chatClient.getName()).thenReturn("User");
        when(chatClient.getConnectedTime()).thenReturn(MyLocalDateTime.getLocalDateTime());
        chatServer.onConnect(chatClient);
        chatServer.onMessage(chatClient, "mock Test Message");
        verify(sender, times(1)).action("Test Message");
    }

    @Test
    public void testOnConnect() {
        ChatClient chatClient = mock(ChatClient.class);
        chatServer.onConnect(chatClient);
        verify(chatClient, times(1)).sendMessage(anyString());
    }

    @Test
    public void testOnDisconnect() {
        ChatClient chatClient = mock(ChatClient.class);
        chatServer.onConnect(chatClient);
        chatServer.onDisconnect(chatClient);
        verify(chatClient, times(2)).sendMessage(anyString());
    }

    @Test
    public void testOnError() {
        ChatClient chatClient = mock(ChatClient.class);
        chatServer.onError(chatClient, new IOException("Error Message"));
        verify(chatClient, times(1)).sendMessage("Error occurred: Error Message");
    }
}
