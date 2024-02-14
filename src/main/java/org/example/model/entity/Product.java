package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "t_order")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String  name;
    private int cost;
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;
}
