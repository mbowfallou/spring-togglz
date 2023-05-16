package com.groupeisi.controller;

import com.groupeisi.dto.Product;
import com.groupeisi.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.togglz.core.Feature;
import org.togglz.core.manager.FeatureManager;
import org.togglz.core.util.NamedFeature;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductController {
    @Autowired
    private ProductService productService;

    @Autowired
    private FeatureManager manager;

    public static final Feature DISCOUNT_APPLIED = new NamedFeature("DISCOUNT_APPLIED");

    @GetMapping("/orders")
    public List<Product> showAvailableProducts(){
        if (manager.isActive(DISCOUNT_APPLIED)){
            return applyDiscount(productService.getAllProducts());
        }
        return productService.getAllProducts();
    }

    private List<Product> applyDiscount(List<Product> availableProducts){
        List<Product> orderListAfterDiscount = new ArrayList<>();

        productService.getAllProducts().forEach(order -> {
            order.setPrice(order.getPrice()*95/100);
            orderListAfterDiscount.add(order);
        });

        return orderListAfterDiscount;
    }
}
