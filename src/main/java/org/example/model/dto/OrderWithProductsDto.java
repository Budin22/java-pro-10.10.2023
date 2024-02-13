package org.example.model.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Data
@NoArgsConstructor
public class OrderWithProductsDto {
    private Integer id;
    private LocalDateTime date;
    private int totalCost;
    private List<Product> products;

    public OrderWithProductsDto(Integer id, LocalDateTime date, int totalCost) {
        this.id = id;
        this.date = date;
        this.totalCost = totalCost;
    }
}
