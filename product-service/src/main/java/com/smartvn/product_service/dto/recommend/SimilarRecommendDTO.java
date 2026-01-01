package com.smartvn.product_service.dto.recommend;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SimilarRecommendDTO {

  @JsonProperty("product_ids")
  private List<Long> productIds;

  private String strategy;
  private Integer count;
}
