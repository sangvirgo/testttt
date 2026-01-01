package com.smartvn.admin_service.dto.interaction;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class InteractionExportDTO {
  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("weight")
  private Integer weight;

  @JsonProperty("type")
  private String type;

  // Getters and setters
}
