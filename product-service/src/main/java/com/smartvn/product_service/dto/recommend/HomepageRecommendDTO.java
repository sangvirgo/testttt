package com.smartvn.product_service.dto.recommend;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class HomepageRecommendDTO {

  @JsonProperty("product_ids")
  private List<Long> productIds;
  private String strategy;
  private Integer count;

}
