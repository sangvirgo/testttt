package com.smartvn.admin_service.controller;

import com.smartvn.admin_service.client.RecommendServiceClient;
import com.smartvn.admin_service.dto.ai.TrainingRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/admin/ai")

public class AdminAIController {

  private final RecommendServiceClient recommendServiceClient;

  @PostMapping("/retrain")
  public ResponseEntity<String> retrainModel(@RequestBody TrainingRequest trainingRequest) {
    try {
      String response = recommendServiceClient.retrainModel(trainingRequest);
      return ResponseEntity.ok(response);
    } catch (Exception e) {
      // Handle Python errors and map them to HTTP 503
      return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body("Error occurred while retraining the model");
    }
  }

}
