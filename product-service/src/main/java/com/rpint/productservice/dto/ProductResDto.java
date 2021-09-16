package com.rpint.productservice.dto;

import lombok.Data;

@Data
public class ProductResDto {

    private String productId;
    private Integer avgReviewScore;
    private Integer noOfReviews;
}
