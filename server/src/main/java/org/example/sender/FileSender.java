package org.example.sender;

import java.io.*;
import java.util.Base64;

public class FileSender implements Sender {
    private final String pathToFolder = System.getenv("PATH_TO_DIRECTORY");

    @Override
    public void action(String message) throws Exception {
        String[] parts = message.split(" ");
        if(parts.length != 2) throw new IllegalArgumentException("not legal message");

        String fileName = parts[0];
        String encodedContent = parts[1];

        byte[] fileContent = Base64.getDecoder().decode(encodedContent);
        String filePath = pathToFolder + fileName;
        File newFile = new File(filePath);

        try(OutputStream writer = new FileOutputStream(newFile)){
            writer.write(fileContent);
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }
    }
}
