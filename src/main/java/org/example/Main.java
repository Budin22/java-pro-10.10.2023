package org.example;

import org.example.algorithm.CocktailSort;
import org.example.algorithm.QuickSort;
import org.example.algorithm.SortAlgorithm;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try{
            SortAlgorithm cocktailSort = new CocktailSort();
            SortAlgorithm quickSort = new QuickSort();

            Integer[] intArray = new Integer[]{6,5,1,-2};

            List<Integer> intList = new ArrayList<>();
            intList.add(10);
            intList.add(2);
            intList.add(-4);
            intList.add(3);

            List<Integer> nullList = null;

            quickSort.sort(intArray);
            quickSort.sort(intList);


//        cocktailSort.sort(intArray);
//        cocktailSort.sort(intList);

            for (Integer s : intArray) {
                System.out.println(s);
            }
            System.out.println("----------------------------------");
            for (Integer s : intList) {
                System.out.println(s);
            }
            quickSort.sort(nullList);
//        cocktailSort.sort(nullList);

        } catch (NullPointerException e){
            System.out.println(e);
        }
    }
}