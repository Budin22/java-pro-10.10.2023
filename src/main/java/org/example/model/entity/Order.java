package org.example.model.entity;

import jakarta.persistence.*;
import lombok.Data;
import org.example.model.Product;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@Table(name = "t_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Integer id;
    @Column(name = "order_date")
    private LocalDateTime date;
    @Column(name = "total_cost")
    private int totalCost;
    @OneToMany(mappedBy = "order", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Product> products;
}
