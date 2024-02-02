package org.example.factory;

import org.example.factory.enums.ChairType;
import org.example.factory.enums.TableType;
import org.example.factory.furniture.chair.Chair;
import org.example.factory.furniture.table.Table;

public interface FurnitureFactory {
    Chair createChair(ChairType type);
    Table createTable(TableType type);
}
