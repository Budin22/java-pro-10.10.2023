package org.example.sender;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

public class ClientFileSender implements Sender {
    private final PrintWriter writer;

    public ClientFileSender(PrintWriter writer) {
        this.writer = writer;
    }

    @Override
    public void action(String pathToFile, Socket socket) throws FileNotFoundException {
        try {
            File readFile = new File(pathToFile);
            String fileAsMessage = convertFileToString(readFile);
            writeMessage(fileAsMessage);
        } catch (Exception e) {
            throw new FileNotFoundException(e.getMessage());
        }
    }

    private void writeMessage(String message) {
        writer.println(message);
        writer.flush();
    }

    public String convertFileToString(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);
        return "-file " + file.getName() + " " + encodedContent;
    }
}
