package org.example.search;

public interface Searchable<T> {
    T search(String path, String target, int maxDeep);
}
