package org.example.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Order {
    private Integer id;
    private Date date;
    private int totalCost;
    private List<Product> products;
}
