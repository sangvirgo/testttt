
package com.smartvn.product_service.client;

import com.smartvn.product_service.dto.recommend.HomepageRecommendDTO;
import com.smartvn.product_service.dto.recommend.SimilarRecommendDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "recommend-service", fallback= RecommendationServiceFallback.class)
public interface RecommendationServiceClient {

  @GetMapping(value = "${api.prefix}/internal/recommend/product-detail/{productId}")
  ResponseEntity<SimilarRecommendDTO> getSimilarProducts(@PathVariable("productId") Long productId,
      @RequestParam("user_id") Long userId,
      @RequestParam("top_k") int topK);
  @GetMapping(value = "${api.prefix}/internal/recommend/homepage")
  ResponseEntity<HomepageRecommendDTO> getFeatureProducts(
    @RequestParam("user_id") Long userId,
      @RequestParam("top_k") int topK);
}
