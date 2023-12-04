package org.example.algorithm;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class QuickSort implements SortAlgorithm {
    @Override
    public <T> List<T> sort(List<T> list, Comparator<T> comparator) {
        return null;
    }

    @Override
    public <T extends Comparable> List<T> sort(List<T> list) {

        if (list.size() <= 1) {
            return list;
        }
        List<T> res = new ArrayList<>();
        List<T> less = new ArrayList<>();
        List<T> more = new ArrayList<>();

        for (int i = 0; i < list.size() - 2; i++) {
            if (list.get(list.size() - 1).compareTo(list.get(i)) < 0) {
                more.add(list.get(i));
            } else {
                less.add(list.get(i));
            }
        }
        System.out.println("less: " + less);
        System.out.println("more: " + more);


        res.addAll(sort(less));
        res.add(list.get(list.size() - 1));
        res.addAll(sort(more));


        return res;
    }

    public<T extends Comparable> void cocktailSort(T[] list){
        int first = 0;
        int last = list.length - 1;
        int i = 0;
        boolean isForward = true;
        int elInx = 0;

        while (true){
            if(first == last) break;
            if(isForward){
                if(list[elInx].compareTo(list[i]) > 0){
                    T tempEl = list[elInx];
                    list[elInx] = list[i];
                    list[i] = tempEl;
                }
            } else {
                if(list[elInx].compareTo(list[i]) < 0){
                    T tempEl = list[elInx];
                    list[elInx] = list[i];
                    list[i] = tempEl;
                }
            }
            elInx = i;

            if(first == i && !isForward){
                isForward = true;
                first++;
            }
            if(last == i && isForward){
                isForward = false;
                last--;
            }
            if(isForward){
                i++;
            } else {
                i--;
            }
        }
    }

    public <T extends Comparable> void cocktailSort(List<T> list){
        int first = 0;
        int last = list.size() - 1;
        int i = 0;
        boolean isForward = true;
        int elInx = 0;

        while (true){
            if(first == last) break;
            if(isForward){
                if(list.get(elInx).compareTo(list.get(i)) > 0){
                    T tempEl = list.get(elInx);
                    list.set(elInx,list.get(i));
                    list.set(i,tempEl);

                }
            } else {
                if((list.get(elInx).compareTo(list.get(i)) < 0)){
                    T tempEl = list.get(elInx);
                    list.set(elInx,list.get(i));
                    list.set(i,tempEl);
                }
            }
            elInx = i;

            if(first == i && !isForward){
                isForward = true;
                first++;
            }
            if(last == i && isForward){
                isForward = false;
                last--;
            }
            if(isForward){
                i++;
            } else {
                i--;
            }
        }
    }

}
