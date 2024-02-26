package org.example.sender;

import java.io.*;

public class FileSender implements Sender {
    @Override
    public void action(String message) {
        File readFile = new File(message);
        String fileName = readFile.getName();
        File writeFile = new File("server/src/main/resources/" + fileName);
        try (
                FileInputStream reader = new FileInputStream(readFile);
                FileOutputStream writer = new FileOutputStream(writeFile)
        ) {
            int content;
            while ((content = reader.read()) != -1) {
                writer.write(content);
            }
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
