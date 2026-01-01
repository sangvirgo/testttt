package com.smartvn.user_service.service.interaction;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

import com.smartvn.user_service.dto.interaction.InteractionExportDTO;
import com.smartvn.user_service.dto.interaction.InteractionRequestDTO;
import com.smartvn.user_service.model.UserInteraction;
import com.smartvn.user_service.repository.UserInteractionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class InteractionService {

  @Autowired
  private UserInteractionRepository userInteractionRepository;

  @Transactional
  public UserInteraction processInteraction(Long userId, InteractionRequestDTO dto) {
    // Step 1: Determine Weight
    int weight = dto.getInteractionType().getWeight();

    // Step 2: 5-Minute Check
    // User already interacted in the last 5 minutes → skip
    Optional<UserInteraction> lastInteraction = Optional.ofNullable(
        userInteractionRepository.findTopByUserIdAndProductIdOrderByCreatedAtDesc(userId, dto.getProductId()));

    if (lastInteraction.isPresent()) {
      Instant lastTime = lastInteraction.get().getCreatedAt();
      Instant fiveMinutesAgo = Instant.now().minus(5, ChronoUnit.MINUTES);

      // Nếu tương tác gần nhất < 5 phút => return
      if (lastTime.isAfter(fiveMinutesAgo)) {
        return null;
      }
    }

    // Nếu chưa có tương tác hoặc đã quá 5 phút => tiếp tục xử lý logic bên dưới

    // Step 3: Save
    UserInteraction interaction = new UserInteraction();
    interaction.setUserId(userId);
    interaction.setInteractionType(dto.getInteractionType());
    interaction.setProductId(dto.getProductId());
    // interaction.setWeight(weight);
    return userInteractionRepository.save(interaction);
  }

  public List<InteractionExportDTO> exportAllInteractions() {
    // Step 1: Fetch ALL UserInteraction entities
    List<UserInteraction> interactions = userInteractionRepository.findAllByOrderByCreatedAtAsc();

    // Step 2: Map each Entity to an InteractionExportDTO
    List<InteractionExportDTO> dtos = interactions.stream()
        .map(InteractionExportDTO::new)
        .toList();

    return dtos;
  }
}
