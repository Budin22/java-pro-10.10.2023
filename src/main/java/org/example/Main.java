package org.example;

import org.example.imageloader.ImageLoader;
import org.example.imageloader.Loader;

public class Main {
    public static void main(String[] args) {
        try(Loader imageLoader = new ImageLoader()) {
            String url = "https://en.wikipedia.org/wiki/Main_Page";
            String directory = "src/";
            imageLoader.load(url, directory);
        } catch (Exception e) {
            System.out.println("Exception in main: " + e.getMessage());
        }
    }
}