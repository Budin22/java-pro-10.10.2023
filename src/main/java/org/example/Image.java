package org.example;

import java.io.Serializable;

public class Image implements Serializable {
    private final String path;
    private final String label;

    public Image(String path, String label) {
        this.path = path;
        this.label = label;
    }

    public String getPath() {
        return "https:" + path;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public String toString() {
        return "{" +
                "path='" + path + '\'' +
                ", label='" + label + '\'' +
                "}\n";
    }
}
