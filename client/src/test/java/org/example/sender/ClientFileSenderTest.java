package org.example.sender;

import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class ClientFileSenderTest {
    @Test
    void action_shouldSendFileMessageToSocket() throws IOException {
        PrintWriter writerMock = mock(PrintWriter.class);
        ClientFileSender sender = new ClientFileSender(writerMock);

        File testFile = File.createTempFile("test", ".txt");
        testFile.deleteOnExit();
        Files.write(testFile.toPath(), "some text".getBytes());

        sender.action(testFile.getAbsolutePath(), mock(Socket.class));

        verify(writerMock).println("-file " + testFile.getName() + " " + encodeFileToBase64(testFile));
        verify(writerMock).flush();
    }

    @Test
    void testAction_FileNotFound(){
        PrintWriter writerMock = mock(PrintWriter.class);
        ClientFileSender sender = new ClientFileSender(writerMock);
        Socket socketMock = mock(Socket.class);
        String nonExistentPath = "";
        assertThrows(FileNotFoundException.class, () -> sender.action(nonExistentPath, socketMock));
    }

    private String encodeFileToBase64(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        return Base64.getEncoder().encodeToString(fileContent);
    }
}
