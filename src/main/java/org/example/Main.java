package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.LinkedList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

//        int pros = Runtime.getRuntime().availableProcessors();
//        System.out.println("Number of processors that JVM can use" + pros);
//
//        List<Image> images = new LinkedList<>();

        try(ImageLoader imageLoader = new ImageLoader()) {

            String url = "https://en.wikipedia.org/wiki/Main_Page";
            String directory = "C:\\Users\\Alena\\Desktop\\images\\";

            imageLoader.load(url, directory);



//            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
//            Elements elements = doc.select("img[alt]");
//            for (Element el : elements) {
//                String src = "https:" + el.attr("src");
//                String alt = el.attr("alt");
//                if (src.trim().length() != 0 && alt.trim().length() != 0 && !src.contains("static")) {
//                    Image image = new Image(src, alt);
//                    images.add(image);
//                }
//            }
//            System.out.println(images);
//
//            for (Image image : images) {
//                URI url = new URI(image.getPath());
//                String[] arr = image.getPath().split("/");
//                String name = arr[arr.length - 1];
//
//                InputStream in = new BufferedInputStream(url.toURL().openStream());
//                Path path = Path.of("C:\\Users\\Alena\\Desktop\\images\\" + name);
//                Files.copy(in, path, StandardCopyOption.REPLACE_EXISTING);
//            }

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}