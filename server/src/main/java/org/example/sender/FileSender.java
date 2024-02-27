package org.example.sender;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileSender implements Sender {
    private String pathToFolder = "src/main/resources/";

    @Override
    public void action(String pathToFile) {
        File readFile = new File(pathToFile);
        String fileName = readFile.getName();
        List<Integer> redFile = readFile(readFile);
        writeFile(redFile, fileName);
    }

    public void writeFile(List<Integer> fileData, String fileName) {
        File file = new File(pathToFolder + fileName);
        try (FileOutputStream writer = new FileOutputStream(file)) {
            for (Integer integer : fileData) {
                writer.write(integer);
                writer.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Integer> readFile(File file) {
        List<Integer> fileData = new ArrayList<>();
        try (FileInputStream reader = new FileInputStream(file)) {
            int content;
            while ((content = reader.read()) != -1) {
                fileData.add(content);
            }
            return fileData;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public String getPathToFolder() {
        return pathToFolder;
    }

    public void setPathToFolder(String pathToFolder) {
        this.pathToFolder = pathToFolder;
    }
}
