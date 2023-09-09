package com.example.ecommerce.controller;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.sevice.ProductService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Data
@RequiredArgsConstructor
@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/products")
    public List<Product> getProducts(){
      return productService.getAllProducts();
    }

    @PostMapping("/products")
    public List<Product> create(@RequestBody Product product){
        return productService.createProduct(product);
    }

    @PutMapping("/products/{id}")
    public Product update(@PathVariable("id") Long id, @RequestBody Product product){
        return productService.updateProduct(id,product);
    }

    @DeleteMapping("/products/{id}")
    public String delete(@PathVariable("id") Long id){
        return productService.deleteProduct(id);
    }

}
