package org.example.navigator;

import java.io.File;
import java.util.List;

public interface Navigator<T> {
    void addFilesFromDirectory(String path);

    void addFile(File file);

    void addFile(String path);

    List<T> find(String path);

    List<T> filterBySize(int maxSize);

    void remove(String path);

    List<T> getAllFiles();

    List<T> getSortedBySize();
}
