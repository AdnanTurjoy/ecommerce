package com.example.ecommerce.sevice;

import com.example.ecommerce.entity.Product;
import com.example.ecommerce.repository.ProductRepository;
import jakarta.persistence.Column;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.RequiredArgsConstructor;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.IOException;
import java.io.InputStream;
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


    public void saveDataFromExcel(MultipartFile file) throws IOException {
        InputStream inputStream = file.getInputStream();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0);
        System.out.println(sheet);
        for (Row row : sheet) {
           // System.out.println(row.getCell(4).getNumericCellValue());
            Product product = new Product();
            product.setProductName(row.getCell(0).getStringCellValue());
            product.setProductType(Product.ProductType.valueOf(row.getCell(1).getStringCellValue()));
            product.setDescription(row.getCell(2).getStringCellValue());
            product.setPrice(row.getCell(3).getNumericCellValue());
            product.setAvailableQuantity((int) row.getCell(4).getNumericCellValue());

            // Set other fields as needed

            productRepository.save(product);
        }



        workbook.close();
        inputStream.close();
    }


}
