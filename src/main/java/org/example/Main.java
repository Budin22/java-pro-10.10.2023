package org.example;

import org.example.builder.Car;
import org.example.factory.ChairFactory;
import org.example.factory.FurnitureFactory;
import org.example.factory.TableFactory;
import org.example.strategy.Rectangle;
import org.example.strategy.StrategyUser;
import org.example.strategy.Triangle;

public class Main {
    public static void main(String[] args) {

        StrategyUser strategyUserRectangle = new StrategyUser(new Rectangle(12.0, 10.0));
        StrategyUser strategyUserTriangle = new StrategyUser(new Triangle(12.0, 10.0));
        strategyUserTriangle.executeStrategy();
        strategyUserRectangle.executeStrategy();


        FurnitureFactory chairFactory = new ChairFactory();
        FurnitureFactory tableFactory = new TableFactory();
        chairFactory.create();
        tableFactory.create();

        Car car = new Car.Builder().setConditioner(true).setBulletProofGlass(false).setExtraInsurance(true).build();

    }
}