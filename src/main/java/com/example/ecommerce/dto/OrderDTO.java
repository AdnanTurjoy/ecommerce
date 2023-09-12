package com.example.ecommerce.dto;

import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class OrderDTO {
    private Order order;
    private List<Product> products;

}
