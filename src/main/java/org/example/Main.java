package org.example;

import org.example.search.ThreadsFileSystemSearch;
import org.example.search.Searchable;
import org.example.search.SyncFileSystemSearch;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
//        ValueCalculator calc = new ValueCalculator(2_000_000);
//        calc.doSomeCalc();

        long start = System.currentTimeMillis();
        Searchable<ArrayList<String>> threadsSearch = new ThreadsFileSystemSearch();
        System.out.println("threads search get matches: " + threadsSearch.search("C:\\Users", "file",  100).size());
        long end = System.currentTimeMillis();
        System.out.println("threads search get time: " + (end - start));

        start = System.currentTimeMillis();
        Searchable<ArrayList<String>> syncSearch = new SyncFileSystemSearch();
        System.out.println("sync search get matches: " + syncSearch.search("C:\\Users", "file",  100).size());
        end = System.currentTimeMillis();
        System.out.println("sync search get time: " + (end - start));
    }
}