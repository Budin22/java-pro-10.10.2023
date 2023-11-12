package org.example;

import java.util.ArrayList;

public class ThreadSafeList {

    private final ArrayList<Integer> list;

    public ThreadSafeList() {
        this.list = new ArrayList<>();
    }

    public void add(Integer element) {

    }

    public Integer get(int index) {
        return list.get(index);
    }

    public void remove(int index) {
        list.remove(index);
    }
}
