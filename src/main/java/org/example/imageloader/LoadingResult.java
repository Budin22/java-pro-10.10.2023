package org.example.imageloader;

import java.util.List;

public class LoadingResult {
    private String url;
    private String dateTime;
    private int success;
    private int scanned;
    private List<Image> images;

    public LoadingResult(String url, int success, int scanned, List<Image> images, String dateTime) {
        this.url = url;
        this.success = success;
        this.scanned = scanned;
        this.images = images;
        this.dateTime = dateTime;
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

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
