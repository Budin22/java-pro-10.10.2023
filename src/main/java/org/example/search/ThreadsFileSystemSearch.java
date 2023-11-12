package org.example.search;

import java.io.File;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadsFileSystemSearch implements Searchable<ArrayList<String>> {
    private ArrayList<String> matches = new ArrayList<>();
    private ArrayList<Future<?>> futures = new ArrayList<>();

    @Override
    public ArrayList<String> search(String path, String target, int maxDeep) {
        int currentDeep = 0;
        searchWithThreads(path, target, currentDeep, maxDeep);
        waitForCompletionAllTasks(futures);
        return matches;
    }

    public void searchWithThreads(String path, String target, int currentDeep, int maxDeep) {
        ExecutorService executor = Executors.newFixedThreadPool(5);
        File directory = new File(path);
        if (directory.exists() && directory.isDirectory()) {
            Future<?> task = executor.submit(() -> findMatchInDirectory(directory, target, currentDeep, maxDeep, executor));
            futures.add(task);
        } else {
            System.out.printf("%s isn't the directory", path);
        }
        executor.shutdown();
    }

    private void waitForCompletionAllTasks(ArrayList<Future<?>> tasks) {
        ArrayList<Future<?>> existFutures = new ArrayList<>(tasks);
        tasks.clear();
        while (true) {
            if (existFutures.size() > 0) {
                existFutures.removeIf(Future::isDone);
            } else if (tasks.size() > 0) {
                existFutures = new ArrayList<>(tasks);
                tasks.clear();
            } else {
                break;
            }
        }
    }

    private void findMatchInDirectory(File directory, String target, int currentDeep, int maxDeep, ExecutorService executor) {
        if (currentDeep > maxDeep) {
            return;
        }

        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    if (!executor.isShutdown()) {
                        Future<?> task = executor.submit(() -> findMatchInDirectory(directory, target, currentDeep + 1, maxDeep, executor));
                        futures.add(task);
                    } else {
                        searchWithThreads(file.getAbsolutePath(), target, currentDeep + 1, maxDeep);
                    }
                } else {
                    if (file.getName().toLowerCase().contains(target.toLowerCase())) {
                        matches.add(file.getAbsolutePath());
                    }
                }
            }
        }
    }
}
