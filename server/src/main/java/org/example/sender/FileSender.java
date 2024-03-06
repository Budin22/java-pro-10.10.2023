package org.example.sender;

import java.io.*;
import java.net.Socket;
import java.util.Base64;

public class FileSender implements Sender {
    private String pathToFolder = "C:\\Users\\38093\\Desktop\\course\\java-pro-10.10.2023\\server\\src\\main\\resources\\";

    @Override
    public void action(String message, Socket socket) {
        String[] parts = message.split(" ");
        String fileName = parts[0];
        String encodedContent = parts[1];

        byte[] fileContent = Base64.getDecoder().decode(encodedContent);
        String filePath = pathToFolder + fileName;
        File newFile = new File(filePath);
//        try {
//            newFile.createNewFile();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }

        try(OutputStream writer = new FileOutputStream(newFile)){
            writer.write(fileContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
