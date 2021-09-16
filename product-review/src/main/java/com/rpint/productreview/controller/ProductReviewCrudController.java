package com.rpint.productreview.controller;

import com.rpint.productreview.reqres.ProductResDto;
import com.rpint.productreview.reqres.ProductReviewUpdateReq;
import com.rpint.productreview.reqres.ProductReviewReq;
import com.rpint.productreview.service.ProductReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductReviewCrudController {

    @ExceptionHandler(IllegalArgumentException.class)
    public String illegalArgumentExceptionHandler(IllegalArgumentException ase) {
        return "ERR_MSG :- " + ase.getMessage();
    }

    @ExceptionHandler(EmptyResultDataAccessException.class)
    public String emptyResultException(EmptyResultDataAccessException ase) {
        return new String("Requested record not found");
    }

    @Autowired
    private ProductReviewService productReviewService;

    @GetMapping("/review/{productId}")
    public ProductResDto getProductById(@PathVariable("productId") String productId) {
        return productReviewService.getProductReviewByProductId(productId);
    }

    @PostMapping("/review")
    public String createProductReview(@RequestBody ProductReviewReq req) {
        productReviewService.createProductReview(req);
        return "Product Review Recorded Successfully";
    }

    @PutMapping("/review")
    public String updateProductReview(@RequestBody ProductReviewUpdateReq updateReq) {
        productReviewService.updateProductReview(updateReq);
        return "Product Review Updated Successfully";
    }

    @DeleteMapping("/review/{id}")
    public String deleteProductReview(@PathVariable("id") Long id) {
        productReviewService.deleteById(id);
        return "Product Review Deleted Successfully";
    }

}
