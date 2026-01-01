
package com.smartvn.admin_service.client;

import com.smartvn.admin_service.config.FeignClientConfig;
import com.smartvn.admin_service.dto.ai.TrainingRequest;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "recommend-service", configuration = FeignClientConfig.class, fallback = RecommendServiceFallBack.class)
public interface RecommendServiceClient {

  @PostMapping("${api.prefix}/internal/recommend/train")
  String retrainModel(@RequestBody TrainingRequest trainingRequest);

}
