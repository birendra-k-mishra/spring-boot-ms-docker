package com.rpint.productreview.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity(name = "product_review")
public class ProductReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_id")
    private String productId;

    @Column(name = "rating")
    private int rating;

    public ProductReview() {

    }

    public ProductReview(String productId, int rating) {
        this.productId = productId;
        this.rating = rating;
    }
}
