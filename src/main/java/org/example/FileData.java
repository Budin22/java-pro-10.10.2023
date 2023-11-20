package org.example;

import java.nio.file.Path;

public class FileData {
    private String name;
    private Path path;
    private Byte[] bytes;

    public FileData() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Path getPath() {
        return path;
    }

    public void setPath(Path path) {
        this.path = path;
    }

    public Byte[] getBytes() {
        return bytes;
    }

    public void setBytes(Byte[] bytes) {
        this.bytes = bytes;
    }
}
