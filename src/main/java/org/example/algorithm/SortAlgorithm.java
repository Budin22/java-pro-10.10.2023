package org.example.algorithm;

import java.util.List;

public interface SortAlgorithm {
    <T extends Comparable<T>> void sort(@NotNull List<T> list) throws NullPointerException;

    <T extends Comparable<T>> void sort(@NotNull T[] list) throws NullPointerException;
}
