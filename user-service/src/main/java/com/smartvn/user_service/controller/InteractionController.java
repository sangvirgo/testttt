package com.smartvn.user_service.controller;

import java.util.Optional;

import com.smartvn.user_service.dto.interaction.InteractionRequestDTO;
import com.smartvn.user_service.model.User;
import com.smartvn.user_service.model.UserInteraction;
import com.smartvn.user_service.repository.UserRepository;
import com.smartvn.user_service.security.jwt.JwtUtils;
import com.smartvn.user_service.service.interaction.InteractionService;
import com.smartvn.user_service.service.user.UserService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@RequiredArgsConstructor
@RequestMapping("${api.prefix}/users")
public class InteractionController {

  private final JwtUtils jwtUtils;
  private final InteractionService interactionService;
  private final UserService userService;

  private final UserRepository userRepository;

  @PostMapping("/interactions")
  public ResponseEntity<?> createInteraction(@RequestHeader(value = "Authorization", required = false) String jwt,
      @RequestBody InteractionRequestDTO dto) {

    if (jwt != null && jwt.startsWith("Bearer ")) {
      jwt = jwt.substring(7);
    } else {
      return ResponseEntity.noContent().build();
    }
    String email = jwtUtils.getEmailFromToken(jwt);
    Optional<User> optionalUser = userRepository.findByEmail(email);
    if (optionalUser.isEmpty()) {
      log.info("Guest user trigger product id ", dto.getProductId());
      return ResponseEntity.noContent().build();
    }

    Long userId = optionalUser.get().getId();
    UserInteraction interaction = interactionService.processInteraction(userId, dto);
    log.info("Receive user interaction");
    log.info(interaction);

    return ResponseEntity.accepted().body(interaction);
  }
}
