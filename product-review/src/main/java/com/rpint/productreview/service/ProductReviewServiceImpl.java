package com.rpint.productreview.service;

import com.rpint.productreview.model.ProductReview;
import com.rpint.productreview.repo.ProductReviewRepository;
import com.rpint.productreview.reqres.ProductResDto;
import com.rpint.productreview.reqres.ProductReviewReq;
import com.rpint.productreview.reqres.ProductReviewUpdateReq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.CollectionUtils;

import java.util.Collection;
import java.util.Optional;

@Service
public class ProductReviewServiceImpl implements ProductReviewService {

    @Autowired
    private ProductReviewRepository reviewRepository;

    @Override
    public void createProductReview(ProductReviewReq productReviewReq) {
        Assert.notNull(productReviewReq, "Invalid request to create Product Review");
        if (productReviewReq.getRating() < 1 || productReviewReq.getRating() > 5) {
            throw new IllegalArgumentException("The range of rating should be between 1 and 5");
        }
        ProductReview productReview = getProductReview(productReviewReq);
        reviewRepository.save(productReview);
    }

    @Override
    public ProductResDto getProductReviewByProductId(String productId) {
        Collection<ProductReview> productReviewCollection = reviewRepository.findByProductId(productId);
        Assert.isTrue(!CollectionUtils.isEmpty(productReviewCollection), "No review found for the requested product");
        ProductResDto resDto = new ProductResDto();
        resDto.setProductId(productId);
        resDto.setAvgReviewScore(getAvgReviewScore(productReviewCollection));
        resDto.setNoOfReviews(productReviewCollection.size());
        return resDto;
    }

    @Override
    public void updateProductReview(ProductReviewUpdateReq updateReq) {
        Assert.notNull(updateReq, "Invalid request to update Product Review");
        Optional<ProductReview> productReviewOptional = reviewRepository.findById(updateReq.getId());
        ProductReview productReview = productReviewOptional.isPresent() ? productReviewOptional.get() : null;
        productReview.setRating(updateReq.getRating());
        reviewRepository.save(productReview);
    }

    @Override
    public void deleteById(Long id) {
        Assert.notNull(id, "Requested id is not valid");
        reviewRepository.deleteById(id);
    }

    // private methods.

    private static ProductReview getProductReview(ProductReviewReq productReviewReq) {
        return new ProductReview(productReviewReq.getProductId(), productReviewReq.getRating());
    }

    private static int getAvgReviewScore(Collection<ProductReview> productReviewCollection) {

        int ratingCount_1 = 0, ratingCount_2 = 0, ratingCount_3 = 0, ratingCount_4 = 0, ratingCount_5 = 0;
        for (ProductReview each : productReviewCollection) {
            if (each.getRating() == 1) {
                ratingCount_1++;
            } else if (each.getRating() == 2) {
                ratingCount_2++;
            } else if (each.getRating() == 3) {
                ratingCount_3++;
            } else if (each.getRating() == 4) {
                ratingCount_4++;
            } else if (each.getRating() == 5) {
                ratingCount_5++;
            }
        }

        int i = (1 * ratingCount_1 + 2 * ratingCount_2 + 3 * ratingCount_3 + 4 * ratingCount_4 + 5 * ratingCount_5) / 5;
        return i;
    }
}
