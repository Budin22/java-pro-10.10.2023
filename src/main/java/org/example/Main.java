package org.example;

import org.example.station.FuelStation;
import org.example.station.PetrolStation;

public class Main {
    public static void main(String[] args) {

        FuelStation station = new PetrolStation(3200);
        for (int i = 1; i < 10; i++) {
            station.doRefuel(100 * i);
        }
    }
}