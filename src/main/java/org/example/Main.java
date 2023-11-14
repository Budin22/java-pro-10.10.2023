package org.example;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class Main {
    public static void main(String[] args) {

        int pros = Runtime.getRuntime().availableProcessors();
        System.out.println("Number of processors that JVM can use" + pros);

        try {
            Document doc = Jsoup.connect("https://ru.wikipedia.org/wiki/%D0%90%D1%80%D1%81%D0%B5%D0%BD%D0%B0%D0%BB_(%D1%84%D1%83%D1%82%D0%B1%D0%BE%D0%BB%D1%8C%D0%BD%D1%8B%D0%B9_%D0%BA%D0%BB%D1%83%D0%B1,_%D0%9B%D0%BE%D0%BD%D0%B4%D0%BE%D0%BD)").get();
            Elements elements = doc.select("img[alt]");
            for (Element el: elements){
                System.out.println(el.attr("src"));
                System.out.println(el.attr("alt"));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}