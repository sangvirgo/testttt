package com.smartvn.admin_service.client;

import com.smartvn.admin_service.dto.ai.TrainingRequest;

import org.springframework.web.client.HttpServerErrorException.ServiceUnavailable;

import jakarta.ws.rs.ServiceUnavailableException;

public class RecommendServiceFallBack implements RecommendServiceClient {

  @Override
  public String retrainModel(TrainingRequest trainingRequest) {
    throw new ServiceUnavailableException("recommend service not avaialbe");

  }

}
