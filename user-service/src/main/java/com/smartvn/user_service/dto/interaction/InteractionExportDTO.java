package com.smartvn.user_service.dto.interaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartvn.user_service.model.UserInteraction;

public class InteractionExportDTO {
    @JsonProperty("user_id")
    private Long userId;

    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("weight")
    private Integer weight;


    @JsonProperty("type")
    private String type;


    public InteractionExportDTO(UserInteraction userInteraction) {
        this.userId = userInteraction.getUserId();
        this.productId = userInteraction.getProductId();
        this.weight = userInteraction.getInteractionType().getWeight();
        this.type = userInteraction.getInteractionType().getValue();

    }

    // Getters and setters
}
