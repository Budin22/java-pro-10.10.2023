package org.example.station;

public interface FuelStation extends AutoCloseable {
    void doRefuel(double fuelAmount);
}
