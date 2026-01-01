package com.smartvn.order_service.dto.interaction;

import java.time.LocalDateTime;

public record OrderItemRelation(Long userId, Long productId, LocalDateTime createdAt) {
}
