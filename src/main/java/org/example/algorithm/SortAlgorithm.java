package org.example.algorithm;

import java.util.List;

public interface SortAlgorithm {
    <T extends Comparable> void sort(List<T> list);
    <T extends Comparable> void sort(T[] list);
}
