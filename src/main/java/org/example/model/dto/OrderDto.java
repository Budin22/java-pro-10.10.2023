package org.example.model.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class OrderDto {
    private Integer id;
    private LocalDateTime date;
    private int totalCost;
}
