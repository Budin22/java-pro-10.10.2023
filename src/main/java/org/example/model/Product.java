package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {
    private Integer id;
    private String  name;
    private int cost;

    public Product(Integer id, String name, int cost) {
        this.id = id;
        this.name = name;
        this.cost = cost;
    }
}
