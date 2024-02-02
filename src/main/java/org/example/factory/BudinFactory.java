package org.example.factory;

import org.example.exception.IllegalTypeException;
import org.example.factory.enums.TableType;
import org.example.factory.furniture.chair.Chair;
import org.example.factory.furniture.chair.DiningChair;
import org.example.factory.furniture.chair.MorningChair;
import org.example.factory.furniture.chair.OfficeChair;
import org.example.factory.enums.ChairType;
import org.example.factory.furniture.table.DiningTable;
import org.example.factory.furniture.table.MorningTable;
import org.example.factory.furniture.table.OfficeTable;
import org.example.factory.furniture.table.Table;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

public class BudinFactory implements FurnitureFactory {
    private final Map<ChairType, Supplier<Chair>> chairs;
    private final Map<TableType, Supplier<Table>> tables;

    public BudinFactory() {
        Map<ChairType, Supplier<Chair>> chairs = new HashMap<>();
        chairs.put(ChairType.DINING_CHAIR, DiningChair::new);
        chairs.put(ChairType.MORNING_CHAIR, MorningChair::new);
        chairs.put(ChairType.OFFICE_CHAIR, OfficeChair::new);
        this.chairs = chairs;

        Map<TableType, Supplier<Table>> tables = new HashMap<>();
        tables.put(TableType.DINING_TABLE, DiningTable::new);
        tables.put(TableType.MORNING_TABLE, MorningTable::new);
        tables.put(TableType.OFFICE_TABLE, OfficeTable::new);
        this.tables = tables;
    }

    @Override
    public Chair createChair(ChairType type) {

        Chair chair = chairs.get(type).get();
        if (chair != null) {
            return chair;
        } else {
            throw new IllegalTypeException("You don't have such type as: " + type);
        }
    }

    @Override
    public Table createTable(TableType type) {
        Table table = tables.get(type).get();
        if (table != null) {
            return table;
        } else {
            throw new IllegalTypeException("You don't have such type as: " + type);
        }
    }
}
