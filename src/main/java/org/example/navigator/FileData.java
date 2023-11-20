package org.example.navigator;

import java.nio.file.Path;

public class FileData {
    private String name;
    private String path;
    private long bytes;

    public FileData() {
    }

    public FileData(String name, String path, long bytes) {
        this.name = name;
        this.path = path;
        this.bytes = bytes;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public long getBytes() {
        return bytes;
    }

    public void setBytes(long bytes) {
        this.bytes = bytes;
    }

    @Override
    public String toString() {
        return "{" +
                "name:'" + name + '\'' +
                ", path:'" + path + '\'' +
                ", bytes:" + bytes +
                '}';
    }
}
