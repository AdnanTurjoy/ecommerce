package com.example.ecommerce.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "product")
public class Product {

    public enum ProductType {
        SMALL, MEDIUM, LARGE, EXTRALARGE;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String productName;

    @Enumerated(EnumType.ORDINAL)
    @Column
    private ProductType productType;

    @Column
    private String description;

    @Column
    private Integer availableQuantity;

    @Column
    private Double price;
}
