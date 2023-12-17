package org.example.factory;

import org.example.factory.furniture.Chair;

public class ChairFactory extends FurnitureFactory{
    @Override
    protected Chair createFurniture() {
        return new Chair();
    }
}
