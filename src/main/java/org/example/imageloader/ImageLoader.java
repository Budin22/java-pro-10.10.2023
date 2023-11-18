package org.example.imageloader;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ImageLoader implements Loader {
    private final List<Future<?>> futures;
    private final List<Image> images;
    private final ExecutorService executor;

    public ImageLoader() {
        int maxThreads = Math.max(2, Runtime.getRuntime().availableProcessors());
        this.executor = Executors.newFixedThreadPool(maxThreads);
        this.futures = new LinkedList<>();
        this.images = new LinkedList<>();
    }

    @Override
    public void load(String url, String pathToDirectory) {
        if (!new File(pathToDirectory).isDirectory()) {
            System.out.printf("You must have correct directory path. Path: %s isn't a directory", pathToDirectory);
            return;
        }

        List<Image> imagesInfo = getAllImagesFromPage(url);
        if (imagesInfo.size() == 0) {
            System.out.printf("Page with path: %s doesn't have any img tags", url);
            return;
        }

        loadImagesToDirectory(imagesInfo, pathToDirectory);
        while (true) {
            if (futures.stream().allMatch(Future::isDone)) {
                break;
            }
        }

        LoadingResult loadingResult = new LoadingResult(url, images.size(), futures.size(), images, getDateTime());
        createJsonFile("src/", "file.json", loadingResult);
    }

    private void createJsonFile(String path, String fileName, Object obj) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            File directory = new File(path + fileName);
            mapper.writeValue(directory, obj);
        } catch (JsonProcessingException e) {
            System.out.println("JsonProcessingException: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("IOException with createJsonFile: " + e.getMessage());
        }
    }

    private String getDateTime() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return dateFormat.format(new Date());
    }

    private void loadImagesToDirectory(List<Image> imageList, String path) {
        for (Image image : imageList) {
            Future<?> future = executor.submit(() -> {
                try {
                    URI url = new URI(image.getPath());
                    InputStream in = new BufferedInputStream(url.toURL().openStream());

                    String name = getFileNameFromImagePath(image.getPath());
                    Path pathWithName = Path.of(path + name);
                    Files.copy(in, pathWithName, StandardCopyOption.REPLACE_EXISTING);
                    images.add(image);
                } catch (MalformedURLException e) {
                    System.out.println("MalformedURLException: " + e.getMessage());
                } catch (URISyntaxException e) {
                    System.out.println("URISyntaxException: " + e.getMessage());
                } catch (IOException e) {
                    System.out.println("IOException with loadImagesToDirectory: " + e.getCause());
                }
            });
            futures.add(future);
        }
    }

    private String getFileNameFromImagePath(String path) {
        String[] arr = path.split("/");
        return arr[arr.length - 1];
    }

    private List<Image> getAllImagesFromPage(String url) {
        List<Image> allImages = new LinkedList<>();
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("img[alt]");
            for (Element el : elements) {
                String src = el.attr("src");
                String alt = el.attr("alt");
                allImages.add(new Image(src, alt));
            }
        } catch (IOException e) {
            System.out.println("IOException with getAllImagesFromPage: " + e.getCause());
        }
        return allImages;
    }

    @Override
    public void close() {
        this.executor.shutdown();
    }
}
