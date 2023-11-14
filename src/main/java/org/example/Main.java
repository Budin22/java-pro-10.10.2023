package org.example;

import org.example.station.FuelStation;
import org.example.station.PetrolStation;

public class Main {
    public static void main(String[] args) {
        try(FuelStation station = new PetrolStation(3200);) {
            for (int i = 1; i < 10; i++) {
                station.doRefuel(100 * i);
            }
        } catch (Exception e){
            System.out.println(e.getMessage());
        }
    }
}