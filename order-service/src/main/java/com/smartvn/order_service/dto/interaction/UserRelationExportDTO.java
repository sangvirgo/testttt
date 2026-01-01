package com.smartvn.order_service.dto.interaction;

import java.time.Instant;
import java.time.ZoneOffset;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartvn.order_service.model.CartItem;
import com.smartvn.order_service.model.OrderItem;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRelationExportDTO {

  @JsonProperty("user_id")
  private Long userId;

  @JsonProperty("product_id")
  private Long productId;

  @JsonProperty("weight")
  private Integer weight;

  @JsonProperty("type")
  private String type;

  @JsonProperty("created_at")
  private Instant createdAt;

  public UserRelationExportDTO(Long userId, Long productId, Integer weight, String type, Instant createdAt) {
    this.userId = userId;
    this.productId = productId;
    this.weight = weight;
    this.type = type;
    this.createdAt = createdAt;
  }

  public static UserRelationExportDTO fromOrderItem(OrderItem orderItem) {

    return new UserRelationExportDTO(orderItem.getOrder().getUserId(), orderItem.getProductId(), 3, "PURCHASE",
        Instant.from(orderItem.getOrder().getCreatedAt().toInstant(null))

    );
  }

  public static UserRelationExportDTO from(OrderItemRelation oi) {

    return new UserRelationExportDTO(oi.userId(), oi.productId(), 3, "PURCHASE",
        oi.createdAt() != null

            ? oi.createdAt().toInstant(ZoneOffset.UTC)
            : null);
  }

  public static UserRelationExportDTO from(CartItemRelation ci) {

    return new UserRelationExportDTO(ci.userId(), ci.productId(), 2, "ADD_TO_CART",
        ci.createdAt() != null
            ? ci.createdAt().toInstant(ZoneOffset.UTC)
            : null);
  }

  public static UserRelationExportDTO fromCartItem(CartItem cartItem) {
    return new UserRelationExportDTO(cartItem.getCart().getUserId(), cartItem.getProductId(), 2, "ADD_TO_CART",
        Instant.from(cartItem.getCreatedAt().toInstant(null)));
  }
}
