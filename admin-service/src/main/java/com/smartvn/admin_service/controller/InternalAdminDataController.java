package com.smartvn.admin_service.controller;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.smartvn.admin_service.client.OrderServiceClient;
import com.smartvn.admin_service.client.ProductServiceClient;
import com.smartvn.admin_service.client.UserServiceClient;
import com.smartvn.admin_service.dto.interaction.InteractionExportDTO;
import com.smartvn.admin_service.dto.product.ProductMetadataDTO;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("${api.prefix}/internal/admin/export")
@RequiredArgsConstructor
@Log4j2
public class InternalAdminDataController {

  private final UserServiceClient userServiceClient;
  private final OrderServiceClient orderServiceClient;
  private final ProductServiceClient productServiceClient;

  // Facade colision with other endpoint
  @GetMapping("/interactions")
  // @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<InteractionExportDTO>> exportAllInteractions() {
    log.debug("Receive internal connection in data pipeline");
    List<InteractionExportDTO> interactions =  userServiceClient.exportAllInteractions().getBody();
    List<InteractionExportDTO> relations = orderServiceClient.exportAllUserRelation().getBody();
    return ResponseEntity.ok(Stream.concat(interactions.stream(), relations.stream()).collect(Collectors.toList()));
  }

  @GetMapping("/products")
  // @PreAuthorize("hasRole('ADMIN')")
  public ResponseEntity<List<ProductMetadataDTO>> exportAllProductMetaData() {
    List<ProductMetadataDTO> products = productServiceClient.exportAllProducts().getBody();
    return ResponseEntity.ok(products);
  }
}
