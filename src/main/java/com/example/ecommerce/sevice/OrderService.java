package com.example.ecommerce.sevice;

import com.example.ecommerce.dto.OrderDTO;
import com.example.ecommerce.entity.Order;
import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class OrderService {
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    public List<Order> getAllOrder(){
        return orderRepository.findAll();
    }

    public OrderDTO getOrder(Long id){
        Order order = orderRepository.findById(id).get();
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrder(order);
        List<Product> products = new ArrayList<>();
        for (int i=0;i<order.getProductIdList().size();i++){
          Optional<Product> prod=  productRepository.findById(Long.valueOf(order.getProductIdList().get(i)));
            products.add(prod.get());
       }

      orderDTO.setProducts(products);
        return orderDTO;
    }

    public Order create(Order order){


        return orderRepository.save(order);
    }
}
