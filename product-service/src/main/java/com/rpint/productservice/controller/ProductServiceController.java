package com.rpint.productservice.controller;

import com.rpint.productservice.dto.ProductServiceRes;
import com.rpint.productservice.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class ProductServiceController {

    @Autowired
    private ProductService productService;

    @GetMapping("/product/{productId}")
    public ProductServiceRes getProductDetails(@PathVariable("productId") String productId) {
        return productService.getProductDetails(productId);
    }
}
