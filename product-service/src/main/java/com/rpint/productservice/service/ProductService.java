package com.rpint.productservice.service;

import com.rpint.productservice.dto.ProductServiceRes;

public interface ProductService {

    String ADIDAS_UK_URL = "https://www.adidas.co.uk/api/products/";

    ProductServiceRes getProductDetails(String productId);
}
