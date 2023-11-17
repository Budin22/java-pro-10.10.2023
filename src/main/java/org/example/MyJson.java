package org.example;

import java.util.List;

public class MyJson {
    private String url;
    private int success;
    private int scanned;
    private List<Image> images;

    public MyJson(String url, int success, int scanned, List<Image> images) {
        this.url = url;
        this.success = success;
        this.scanned = scanned;
        this.images = images;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }

    public int getScanned() {
        return scanned;
    }

    public void setScanned(int scanned) {
        this.scanned = scanned;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }

    @Override
    public String toString() {
        return "{" +
                "url='" + url + '\'' +
                ", success=" + success +
                ", scanned=" + scanned +
                ", images=" + images +
                '}';
    }
}
