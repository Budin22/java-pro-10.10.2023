package org.example.search;

import java.io.File;
import java.util.ArrayList;

public class SyncFileSystemSearch implements Searchable<ArrayList<String>> {
    public ArrayList<String> matches = new ArrayList<>();

    @Override
    public ArrayList<String> search(String path, String target, int maxDeep) {
        int currentDeep = 0;
        syncSearch(path, target, currentDeep, maxDeep);
        return matches;
    }

    public void syncSearch(String path, String target, int currentDeep, int maxDeep) {
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            findMatchInDirectory(directory, target, currentDeep, maxDeep);
        } else {
            System.out.printf("%s isn't the directory", path);
        }
    }

    private void findMatchInDirectory(File directory, String target, int currentDeep, int maxDeep) {
        if (currentDeep > maxDeep) {
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    findMatchInDirectory(file, target, currentDeep + 1, maxDeep);
                } else {
                    if (file.getName().toLowerCase().contains(target.toLowerCase())) {
                        matches.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
