package org.example.station;

import org.example.list.ThreadSaveList;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation implements FuelStation {
    private double amount;
    private final Lock readLock;
    private final Lock writeLock;
    private final Semaphore available;
    private final ThreadSaveList threadSaveList;
    private final ExecutorService executor;
    public PetrolStation(double amount) {
        this.amount = amount;
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
        this.threadSaveList = new ThreadSaveList();
        this.executor = Executors.newCachedThreadPool();
        this.available = new Semaphore(3);
    }

    @Override
    public void doRefuel(double fuelAmount) {
        executor.submit(() -> {
            try {
                available.acquire();
                writeLock.lock();
                Thread.sleep(getRandomNum(3, 10) * 1000);
                if (getAmount() > fuelAmount) {
                    this.amount = getAmount() - fuelAmount;
                    threadSaveList.add(String.valueOf(fuelAmount));
                    System.out.println("fuel left: " + getAmount());
                    System.out.println("list with all refuel: " + getListOfRefuel());
                } else {
                    System.out.printf("Station have only: %s, and you need: %s\n", getAmount(), fuelAmount);
                }
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            } finally {
                writeLock.unlock();
                available.release();
            }
        });
    }

    private long getRandomNum(int min, int max) {
        return (long) (Math.random() * (max - min) + 1 + min);
    }

    private double getAmount() {
        readLock.lock();
        try {
            return this.amount;
        } finally {
            readLock.unlock();
        }
    }

    private ThreadSaveList getListOfRefuel() {
        return this.threadSaveList;
    }

    @Override
    public void close() {
        this.executor.shutdown();
    }
}
