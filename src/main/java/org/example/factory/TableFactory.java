package org.example.factory;

import org.example.factory.furniture.Furniture;
import org.example.factory.furniture.Table;

public class TableFactory extends FurnitureFactory{
    @Override
    protected Furniture createFurniture() {
        return new Table();
    }
}
