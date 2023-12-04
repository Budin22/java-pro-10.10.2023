package org.example.algorithm;

import java.util.Comparator;
import java.util.List;

public interface SortAlgorithm {
    <T> List<T> sort(List<T> list, Comparator<T> comparator);
    <T extends Comparable> List<T> sort(List<T> list);

}
