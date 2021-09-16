package com.rpint.productservice.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
public class ProductServiceRes {
    private ProductResDto productResDto;
    private Map<String, Object> adidasRes;
}
