package org.example;


import org.example.algorithm.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();

        List<Integer> list = new ArrayList<>();
        list.add(3);
        list.add(2);
        list.add(8);
        list.add(1);
        list.add(4);

        List<Integer> sortedList = quickSort.sort(list);
        System.out.println(sortedList);
    }
}