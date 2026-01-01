package com.smartvn.user_service.dto.interaction;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.smartvn.user_service.enums.InteractionType;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder



public class InteractionRequestDTO {
    @JsonProperty("product_id")
    private Long productId;

    @JsonProperty("interaction_type")
    private InteractionType interactionType;

    // Getters and setters
}
