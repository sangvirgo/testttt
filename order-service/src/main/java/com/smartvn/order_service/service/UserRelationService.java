package com.smartvn.order_service.service;

import java.util.List;
import java.util.stream.Stream;

import com.smartvn.order_service.dto.interaction.UserRelationExportDTO;
import com.smartvn.order_service.repository.CartItemRepository;
import com.smartvn.order_service.repository.OrderItemRepository;

import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserRelationService {

  private final OrderItemRepository orderItemRepository;

  private final CartItemRepository cartItemRepository;

  public List<UserRelationExportDTO> exportAllUserRelations() {
    List<UserRelationExportDTO> cartRelations = cartItemRepository.findAllByUserRelation().stream()
        .map(UserRelationExportDTO::from).toList();

    List<UserRelationExportDTO> orderRelations = orderItemRepository.findAllByUserRelation().stream()
        .map(UserRelationExportDTO::from).toList();

    log.debug("Cart relations: {}", cartRelations.size());
    log.debug("Order relations: {}", orderRelations.size());

    // Merge the two lists safely
    return Stream.concat(cartRelations.stream(), orderRelations.stream())
        .toList();
  }
}
