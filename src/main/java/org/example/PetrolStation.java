package org.example;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PetrolStation {
    private double amount;
    private final Lock readLock;
    private final Lock writeLock;

    private final int MAX_THREADS = 3;
    private final Semaphore available = new Semaphore(MAX_THREADS);
    private final ThreadSaveList threadSaveList;


    public PetrolStation(double amount) {
        this.amount = amount;
        ReadWriteLock lock = new ReentrantReadWriteLock(true);
        this.readLock = lock.readLock();
        this.writeLock = lock.writeLock();
        this.threadSaveList = new ThreadSaveList();
    }

    public void doRefuel(double fuelAmount) {
        ExecutorService executor = Executors.newCachedThreadPool();
        executor.submit(() -> {
            try {
                available.acquire();

                Thread.sleep(getRandomNum(3, 10) * 1000);
                writeLock.lock();
                if(getAmount() > fuelAmount){
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
        executor.shutdown();
    }

    private long getRandomNum(int min, int max) {
        return (long) (Math.random() * (max - min) + 1 + min);
    }

    private double getAmount() {
        try {
            readLock.lock();
            return this.amount;
        } finally {
            readLock.unlock();
        }
    }

    public ThreadSaveList getListOfRefuel(){
        return this.threadSaveList;
    }
}
