package org.example.navigator;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class FileNavigator implements Navigator<FileData> {
    private final Map<String, List<FileData>> files;

    public FileNavigator() {
        this.files = new HashMap<>();
    }

    @Override
    public void addFilesFromDirectory(String path) {
        File rootFile = new File(path);
        if (!checkIfFileIsDirectory(rootFile)) return;

        File[] files = rootFile.listFiles();
        if (files == null) return;
        for (File file : files) {
            addFile(file);
        }
    }

    @Override
    public void addFile(File file) {
        if (file.isDirectory()) return;
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

    @Override
    public void addFile(String path) {
        File file = new File(path);
        if (file.isDirectory() || !file.exists()) return;
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

    @Override
    public List<FileData> find(String path) {
        File file = new File(path);
        return checkIfFileIsDirectory(file) ? files.get(file.getAbsolutePath()) : null;
    }

    @Override
    public List<FileData> filterBySize(int maxSize) {
        return getAllFiles()
                .stream()
                .filter(item -> item.getBytes() <= maxSize)
                .collect(Collectors.toList());
    }

    @Override
    public void remove(String path) {
        File file = new File(path);
        files.remove(file.getAbsolutePath());
    }

    @Override
    public List<FileData> getAllFiles() {
        List<FileData> allFiles = new LinkedList<>();
        files.forEach((path, fileList) -> allFiles.addAll(fileList));
        return allFiles;
    }

    @Override
    public List<FileData> getSortedBySize() {
        return getAllFiles()
                .stream()
                .sorted(Comparator.comparingLong(FileData::getBytes))
                .collect(Collectors.toList());
    }

    private boolean checkIfFileIsDirectory(File file) {
        if (!file.isDirectory()) {
            System.out.println(file.getAbsolutePath() + ": is not a directory");
            return false;
        }
        return true;
    }
}
