package com.example.ecommerce.sevice;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ProductService {
     private final ProductRepository productRepository;
    public List<Product> getAllProducts(){
      return productRepository.findAll();
    }

    public List<Product> createProduct(Product product){
        return Collections.singletonList(productRepository.save(product));
    }

    public Product updateProduct(Long id, Product product){
        Product item = productRepository.findById(id).get();
        item.setProductName(product.getProductName());
        item.setProductType(product.getProductType());
        item.setDescription(product.getDescription());
        item.setPrice(product.getPrice());
       return productRepository.save(item);

    }

    public String deleteProduct(Long id){
        Optional<Product> item = Optional.of(productRepository.findById(id).get());
        if(item.isPresent()){
            productRepository.deleteById(id);
            return "Deleted Successfully!";
        }else {
            return "Did not Found this product!";
        }
    }

}
