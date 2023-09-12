package com.example.ecommerce.controller;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.sevice.OrderService;

import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    @GetMapping("/orders")
    public List<Order> getAllOrder(){
        return orderService.getAllOrder();
    }
    @GetMapping("/getOrder/{id}")
    public OrderDTO getOrder(@PathVariable("id") Long id){
        return orderService.getOrder(id);
    }

   @PostMapping("/newOrders")
    public Order createOrder(@RequestBody Order order){
        System.out.println(order);

        return orderService.create(order);
   }
}
