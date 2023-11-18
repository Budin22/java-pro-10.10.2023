package org.example.imageloader;

public class Image {
    private final String path;
    private final String label;

    public Image(String path, String label) {
        this.path = path;
        this.label = label;
    }

    public String getPath() {
        return "https:" + path;
    }

    @Override
    public String toString() {
        return "{" +
                "path='" + path + '\'' +
                ", label='" + label + '\'' +
                "}\n";
    }
}
