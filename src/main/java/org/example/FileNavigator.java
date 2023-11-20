package org.example;

import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class FileNavigator {
    private Map<Path, List<FileData>> files;

    public void add(FileData file){
        List<FileData> existList = files.get(file.getPath());
        if(existList != null){
            existList.add(file);
        } else {
            List<FileData> newList = new LinkedList<>();
            newList.add(file);
            files.put(file.getPath(), newList);
        }
    }

    public List<FileData> find(Path path){
        return files.get(path);
    }

    public List<FileData> filterBySize(int maxSize){
        return getAllFiles()
                .stream()
                .filter(item -> item.getBytes().length <= maxSize)
                .collect(Collectors.toList());
    }

    public void remove(Path path){
        files.remove(path);
    }

    private List<FileData> getAllFiles(){
        List<FileData> allFiles = new LinkedList<>();
        files.forEach((path, fileList) -> allFiles.addAll(fileList));
        return allFiles;
    }

    public List<FileData> sortBySize(){
        return null;
    }

}
