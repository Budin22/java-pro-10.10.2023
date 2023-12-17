package org.example.factory;

import org.example.factory.furniture.Furniture;

public abstract class FurnitureFactory {
    public  Furniture create(){
        Furniture furniture = createFurniture();
        furniture.make();
        return furniture;
    }

    protected abstract Furniture createFurniture();
}
