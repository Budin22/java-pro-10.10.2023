package org.example;

import org.example.builder.Car;
import org.example.factory.BudinFactory;
import org.example.factory.FurnitureFactory;
import org.example.factory.enums.ChairType;
import org.example.factory.enums.TableType;
import org.example.factory.furniture.chair.Chair;
import org.example.factory.furniture.table.Table;
import org.example.strategy.Rectangle;
import org.example.strategy.StrategyUser;
import org.example.strategy.Triangle;

public class Main {
    public static void main(String[] args) {

        StrategyUser strategyUserRectangle = new StrategyUser(new Rectangle(12.0, 10.0));
        StrategyUser strategyUserTriangle = new StrategyUser(new Triangle(12.0, 10.0));
        strategyUserTriangle.executeStrategy();
        strategyUserRectangle.executeStrategy();

        FurnitureFactory budinFactory = new BudinFactory();
        Chair kitchenChair = budinFactory.createChair(ChairType.DINING_CHAIR);
        Table kitchenTable = budinFactory.createTable(TableType.DINING_TABLE);

        Car car = new Car.Builder().setConditioner(true).setBulletProofGlass(false).setExtraInsurance(true).build();
    }
}