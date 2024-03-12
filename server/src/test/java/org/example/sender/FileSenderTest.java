package org.example.sender;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.util.Base64;

import static org.junit.jupiter.api.Assertions.*;

public class FileSenderTest {

    @Test
    void action_shouldThrowIllegalArgumentException() {
        FileSender fileSender = new FileSender();

        String notValidMessage = "-file asdasasd dsfsdfsd";
        assertThrows(IllegalArgumentException.class, () -> fileSender.action(notValidMessage));
    }

    @Test
    void action_shouldWriteFile() throws Exception {
        FileSender fileSender = new FileSender();
        String fileName = "testFile.txt";
        String fileContent = "test content";
        String encodedContent = Base64.getEncoder().encodeToString(fileContent.getBytes());

        String message = fileName + " " + encodedContent;
        fileSender.action(message);

        String absPathToCreatedFile = System.getenv("PATH_TO_DIRECTORY") + fileName;
        File createdFile = new File(absPathToCreatedFile);
        String contentInCreatedFile = Files.readString(createdFile.toPath());

        assertEquals(fileContent, contentInCreatedFile);
        createdFile.deleteOnExit();
    }
}
