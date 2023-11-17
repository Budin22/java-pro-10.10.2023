package org.example;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageLoader implements AutoCloseable {
    private String url;
    private int success = 0;
    private final List<Future<?>> futures;
    private final List<Image> images;

    private final ExecutorService executor;
    public ImageLoader() {
        int maxThreads = Math.max(2, Runtime.getRuntime().availableProcessors());
        this.executor = Executors.newFixedThreadPool(maxThreads);
        this.futures = new LinkedList<>();
        this.images = new LinkedList<>();

    }

    public void load(String url, String pathToDirectory) {
        this.url = url;
        List<Image> imagesInfo = getAllImages(url);
        loadImagesToDirectory(imagesInfo, pathToDirectory, executor);

        while (true){
            if(futures.stream().allMatch(Future::isDone)){
                break;
            }
        }

        createJsonFile();


    }

    private void createJsonFile(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            MyJson myJson = new MyJson(url, images.size(), futures.size(), images);
            System.out.println(mapper.writeValueAsString(myJson));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void loadImagesToDirectory(List<Image> imageList, String path, ExecutorService executor) {
        for (Image image : imageList) {
            Future<?> future = executor.submit(() -> {
                try {
                    URI url = new URI(image.getPath());
                    InputStream in = new BufferedInputStream(url.toURL().openStream());

                    String name = getFileNameFromImagePath(image.getPath());
                    Path pathWithName = Path.of(path + name);
                    Files.copy(in, pathWithName, StandardCopyOption.REPLACE_EXISTING);
                    success++;
                    images.add(image);
                } catch (MalformedURLException e) {
                    throw new RuntimeException(e);
                } catch (URISyntaxException e) {
                    throw new RuntimeException(e);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            });
            futures.add(future);
        }
    }

//    private boolean checkValidAttribute(String src, String alt) {
//        return src.trim().length() != 0 && alt.trim().length() != 0 && !src.contains("static");
//    }

    private String getFileNameFromImagePath(String path) {
        String[] arr = path.split("/");
        return arr[arr.length - 1];
    }

    private List<Image> getAllImages(String url) {
        List<Image> imagesInfo;
        try {
            Document doc = Jsoup.connect("https://en.wikipedia.org/wiki/Main_Page").get();
            Elements elements = doc.select("img[alt]");
            imagesInfo = new ArrayList<>();
            for (Element el : elements) {
                String src = el.attr("src");
                String alt = el.attr("alt");
                imagesInfo.add(new Image(src, alt));
//                if (checkValidAttribute(src, alt)) {
//                    imagesInfo.add(new Image(src, alt));
//                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return imagesInfo;
    }

    private String getUrl() {
        return url;
    }

    private int getSuccess() {
        return success;
    }

    private List<Future<?>> getFutures() {
        return futures;
    }

    @Override
    public String toString() {
        return "{" +
                "\nurl='" + url + '\'' +
                ",\n success=" + success +
                ",\n futures=" + futures.size() +
                ",\n images=" + images +
                '}';
    }

    @Override
    public void close() throws Exception {
        this.executor.shutdown();
    }
}
