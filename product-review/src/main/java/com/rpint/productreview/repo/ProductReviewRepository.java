package com.rpint.productreview.repo;

import com.rpint.productreview.model.ProductReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface ProductReviewRepository extends JpaRepository<ProductReview, Long> {

    Optional<ProductReview> findById(Long id);

    Collection<ProductReview> findByProductId(String productId);
}
