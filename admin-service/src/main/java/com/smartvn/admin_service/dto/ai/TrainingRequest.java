package com.smartvn.admin_service.dto.ai;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class TrainingRequest {

  @Schema(description = "Force complete retraining")
  @JsonProperty("force_retrain_all")
  @NotNull
  private boolean forceRetrainAll;

  @Schema(description = "Version tag for models")
  @JsonProperty("model_version_tag")
  @Size(max = 255)
  private String modelVersionTag;

  public TrainingRequest() {
    // Default constructor
  }

  public TrainingRequest(boolean forceRetrainAll, String modelVersionTag) {
    this.forceRetrainAll = forceRetrainAll;
    this.modelVersionTag = modelVersionTag;
  }

}
