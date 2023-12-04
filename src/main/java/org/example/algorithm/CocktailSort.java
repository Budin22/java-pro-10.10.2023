package org.example.algorithm;

import java.util.List;

public class CocktailSort implements SortAlgorithm {
    @Override
    public <T extends Comparable> void sort(List<T> list) {
        cocktailSort(list, 0, list.size() - 1);
    }

    @Override
    public <T extends Comparable> void sort(T[] list) {
        cocktailSort(list, 0, list.length - 1);
    }

    public <T extends Comparable> void cocktailSort(T[] list, int first, int last) {
        int i = 0;
        boolean isForward = true;
        int elInx = 0;

        while (true) {
            if (first == last) break;
            if (isForward) {
                if (list[elInx].compareTo(list[i]) > 0) {
                    exchangeValues(list, elInx, i);
                }
            } else {
                if (list[elInx].compareTo(list[i]) < 0) {
                    exchangeValues(list, elInx, i);
                }
            }
            elInx = i;

            if (first == i && !isForward) {
                isForward = true;
                first++;
            }
            if (last == i && isForward) {
                isForward = false;
                last--;
            }
            if (isForward) {
                i++;
            } else {
                i--;
            }
        }
    }

    public <T extends Comparable> void cocktailSort(List<T> list, int first, int last) {
        int i = 0;
        boolean isForward = true;
        int elInx = 0;

        while (true) {
            if (first == last) break;
            if (isForward) {
                if (list.get(elInx).compareTo(list.get(i)) > 0) {
                    exchangeValues(list, elInx, i);

                }
            } else {
                if ((list.get(elInx).compareTo(list.get(i)) < 0)) {
                    exchangeValues(list, elInx, i);
                }
            }
            elInx = i;

            if (first == i && !isForward) {
                isForward = true;
                first++;
            }
            if (last == i && isForward) {
                isForward = false;
                last--;
            }
            if (isForward) {
                i++;
            } else {
                i--;
            }
        }
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
