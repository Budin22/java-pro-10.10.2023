package org.example;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ThreadSaveList {
    private final List<String> list;
    private final Lock readLock;
    private final Lock writeLock;

    public ThreadSaveList() {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
        this.list = new LinkedList<>();
    }

    public String get(int index) {
        readLock.lock();
        try {
            return list.get(index);
        } finally {
            readLock.unlock();
        }

    }

    public void add(String item) {
        writeLock.lock();
        try{
            list.add(item);
        } finally {
            writeLock.unlock();
        }

    }

    public void remove(String index) {
        writeLock.lock();
        try{
            list.remove(index);
        } finally {
            writeLock.unlock();
        }
    }

    @Override
    public String toString() {
        return String.valueOf(list);
    }
}
