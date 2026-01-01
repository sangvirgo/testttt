package com.smartvn.product_service.client;

import java.util.List;

import com.smartvn.product_service.dto.recommend.HomepageRecommendDTO;
import com.smartvn.product_service.dto.recommend.SimilarRecommendDTO;
import com.smartvn.product_service.repository.ProductRepository;

import org.springdoc.core.converters.models.Pageable;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@RequiredArgsConstructor
@Log4j2
public class RecommendationServiceFallback implements RecommendationServiceClient {
  private final ProductRepository productRepository;

  @Override
  public ResponseEntity<SimilarRecommendDTO> getSimilarProducts(Long productId, Long userId, int topK) {
    log.info("Fallback recommend service in get similar product");
    List<Long> topSellingIds = productRepository.findTopSellingProductIds(PageRequest.of(0, topK));

    return ResponseEntity
        .ok(new SimilarRecommendDTO(topSellingIds, "query-top-selling-fallback", topSellingIds.size()));
  }

  @Override
  public ResponseEntity<HomepageRecommendDTO> getFeatureProducts(Long userId, int topK) {
    log.info("Fallback recommend service in get homepage product");
    List<Long> topSellingIds = productRepository.findTopSellingProductIds(PageRequest.of(0, topK));

    return ResponseEntity
        .ok(new HomepageRecommendDTO(topSellingIds, "query-top-selling-fallback", topSellingIds.size()));
  }

}
