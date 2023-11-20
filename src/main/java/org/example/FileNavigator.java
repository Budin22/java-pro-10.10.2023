package org.example;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FileNavigator {
    private final Map<String, List<FileData>> files;

    public FileNavigator(){
        this.files = new HashMap<>();
    }

    public void run(String path) {
        File rootFile = new File(path);
        if(rootFile.isDirectory()){
            File[] files = rootFile.listFiles();
            if(files == null) return;
            for (File file : files){
                add(file);
            }
        } else {
            System.out.println(path + "is not a directory");
        }
    }

    public void add(File file) {
        if(file.isDirectory()) return;
        String pathToDirectory = file.getParentFile().getAbsolutePath();
        List<FileData> existList = files.get(pathToDirectory);
        FileData data = new FileData(file.getName(), pathToDirectory, file.length());
        if (existList != null) {
            existList.add(data);
        } else {
            List<FileData> newList = new LinkedList<>();
            newList.add(data);
            files.put(pathToDirectory, newList);
        }
    }

    public void add(String path) {
        File file = new File(path);
        if(file.isDirectory() || !file.exists()) return;
        String pathToDirectory = file.getParentFile().getAbsolutePath();
        List<FileData> existList = files.get(pathToDirectory);
        FileData data = new FileData(file.getName(), pathToDirectory, file.length());
        if (existList != null) {
            existList.add(data);
        } else {
            List<FileData> newList = new LinkedList<>();
            newList.add(data);
            files.put(pathToDirectory, newList);
        }
    }

    public List<FileData> find(String path) {
        File file = new File(path);
        if(!file.isDirectory()){
            System.out.println(path + ": is not a directory");
            return null;
        }
        return files.get(file.getAbsolutePath());
    }

    public List<FileData> filterBySize(int maxSize) {
        return getAllFiles()
                .stream()
                .filter(item -> item.getBytes() <= maxSize)
                .collect(Collectors.toList());
    }

    public void remove(String path) {
        files.remove(path);
    }

    public List<FileData> getAllFiles() {
        List<FileData> allFiles = new LinkedList<>();
        files.forEach((path, fileList) -> allFiles.addAll(fileList));
        return allFiles;
    }

    public List<FileData> getSortedBySize() {
        return getAllFiles()
                .stream()
                .sorted(Comparator.comparingLong(FileData::getBytes))
                .collect(Collectors.toList());
    }
}
