package com.rpint.productreview.service;

import com.rpint.productreview.reqres.ProductResDto;
import com.rpint.productreview.reqres.ProductReviewReq;
import com.rpint.productreview.reqres.ProductReviewUpdateReq;

public interface ProductReviewService {

    void deleteById(Long id);

    void updateProductReview(ProductReviewUpdateReq updateReq);

    void createProductReview(ProductReviewReq productReviewReq);

    ProductResDto getProductReviewByProductId(String productId);

}
