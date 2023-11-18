package org.example.imageloader;

public interface Loader extends AutoCloseable {
    void load(String url, String pathToDirectory);
}
