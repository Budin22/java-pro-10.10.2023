package org.example;


import org.example.algorithm.QuickSort;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        QuickSort quickSort = new QuickSort();


        Integer[] arr = new Integer[]{-1, 5, -2,15,0};

        List<Integer> list = new ArrayList<>();
        list.add(10);
        list.add(2);
        list.add(-4);
        list.add(3);

//        quickSort.selectSort(arr);
        quickSort.cocktailSort(list);

        for (Integer s : list) {
            System.out.println(s);
        }
    }
}