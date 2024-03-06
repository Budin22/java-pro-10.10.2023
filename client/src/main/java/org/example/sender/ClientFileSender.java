package org.example.sender;

import java.io.*;
import java.net.Socket;
import java.nio.file.Files;
import java.util.Base64;

public class ClientFileSender implements Sender {
    @Override
    public void action(String pathToFile, Socket socket) throws IOException {
        File readFile = new File(pathToFile);
        String fileAsMessage = convertFileToString(readFile);
        writeMessage(fileAsMessage, socket);
    }

    private void writeMessage(String message, Socket socket) throws IOException {
        OutputStream outputStream = socket.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream));
        writer.println(message);
        writer.flush();
    }

    public String convertFileToString(File file) throws IOException {
        byte[] fileContent = Files.readAllBytes(file.toPath());
        String encodedContent = Base64.getEncoder().encodeToString(fileContent);
        return "-file " + file.getName() + " " + encodedContent;
    }
}
