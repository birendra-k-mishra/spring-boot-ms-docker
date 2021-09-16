package com.rpint.productreview.reqres;

import lombok.Data;

@Data
public class ProductResDto {

    private String productId;
    private Integer avgReviewScore;
    private Integer noOfReviews;
}
