package org.example.algorithm;

import java.util.List;

public class QuickSort implements SortAlgorithm {
    @Override
    public <T extends Comparable> void sort(List<T> list) {
        quickSort(list, 0, list.size() - 1);
    }

    @Override
    public <T extends Comparable> void sort(T[] list) {
        quickSort(list, 0, list.length - 1);
    }

    private <T extends Comparable> void quickSort(T[] list, int first, int last) {
        if (first >= last) return;
        int pivotInx = findPivotInx(list, first, last);
        quickSort(list, first, pivotInx - 1);
        quickSort(list, pivotInx + 1, last);
    }

    private <T extends Comparable> void quickSort(List<T> list, int first, int last) {
        if (first >= last) return;
        int pivotInx = findPivotInx(list, first, last);
        quickSort(list, first, pivotInx - 1);
        quickSort(list, pivotInx + 1, last);
    }

    private <T extends Comparable> int findPivotInx(T[] list, int first, int last) {
        int inx = first;
        T pivot = list[last];
        for (int i = first; i <= last; i++) {
            if (list[i].compareTo(pivot) <= 0) {
                exchangeValues(list, inx, i);
                inx++;
            }
        }
        return inx - 1;
    }

    private <T extends Comparable> int findPivotInx(List<T> list, int first, int last) {
        int inx = first;
        T pivot = list.get(last);
        for (int i = first; i <= last; i++) {
            if (list.get(i).compareTo(pivot) <= 0) {
                exchangeValues(list, inx, i);
                inx++;
            }
        }
        return inx - 1;
    }

    private <T> void exchangeValues(List<T> list, int i, int j) {
        T tempEl = list.get(i);
        list.set(i, list.get(j));
        list.set(j, tempEl);
    }

    private <T> void exchangeValues(T[] list, int i, int j) {
        T tempEl = list[i];
        list[i] = list[j];
        list[j] = tempEl;
    }

}
