package com.smartvn.order_service.dto.interaction;

import java.time.Instant;
import java.time.LocalDateTime;

public record CartItemRelation(Long userId, Long productId, LocalDateTime createdAt) {

}
