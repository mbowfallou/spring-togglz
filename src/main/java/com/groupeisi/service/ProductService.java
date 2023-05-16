package com.groupeisi.service;

import com.groupeisi.dto.Product;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ProductService {
    public List<Product> getAllProducts(){
        return Stream.of(
                new Product(1, "Mobile", 50000),
                new Product(2, "Headphone", 2000),
                new Product(3, "Watch", 14999),
                new Product(4, "Glass", 999)
        ).collect(Collectors.toList());
    }
}
