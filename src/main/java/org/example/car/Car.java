package org.example.car;

public class Car {
    public void start(){
        startElectricity();
        startCommand();
        startFuelSystem();
    }
    private void startElectricity(){
        System.out.println("Electricity start");
    }

    private void startCommand(){
        System.out.println("Command start");
    }

    private void startFuelSystem(){
        System.out.println("Fuel System start");
    }
}
