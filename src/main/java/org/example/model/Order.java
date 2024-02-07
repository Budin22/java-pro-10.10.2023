package org.example.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class Order {
    private Integer id;
    private LocalDateTime date;
    private int totalCost;
    private List<Product> products;

    public Order(Integer id, LocalDateTime date, int totalCost) {
        this.id = id;
        this.date = date;
        this.totalCost = totalCost;
    }
}
