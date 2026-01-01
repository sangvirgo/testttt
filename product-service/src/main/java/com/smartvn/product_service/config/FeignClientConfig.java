package com.smartvn.product_service.config;

import com.smartvn.product_service.client.OrderServiceFallback;
import com.smartvn.product_service.client.RecommendationServiceFallback;
import com.smartvn.product_service.client.UserServiceFallback;
import com.smartvn.product_service.repository.ProductRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import feign.RequestInterceptor;
import feign.RequestTemplate;

@Configuration
public class FeignClientConfig {

  @Value("${internal.api.key}")
  private String internalApiKey;

  @Bean
  public RequestInterceptor requestInterceptor() {
    return new RequestInterceptor() {
      @Override
      public void apply(RequestTemplate template) {
        template.header("X-API-KEY", internalApiKey);
      }
    };
  }

  @Bean
  public RecommendationServiceFallback recommendationServiceFallback(ProductRepository productRepository) {
    return new RecommendationServiceFallback(productRepository);
  }


  @Bean
  public OrderServiceFallback orderServiceFallback() {
    return new OrderServiceFallback();
  }
  @Bean
  public UserServiceFallback userServiceFallback(){
    return new UserServiceFallback();
  }

}
