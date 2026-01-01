
package com.smartvn.user_service.controller;

import java.util.List;

import com.smartvn.user_service.dto.interaction.InteractionExportDTO;
import com.smartvn.user_service.service.interaction.InteractionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RestController
@Log4j2
@AllArgsConstructor
// @RequestMapping("${api.prefix}/users/internal/export")
@RequestMapping("${api.prefix}/internal/users/export")
public class InternalInteractionController {

  private InteractionService interactionService;

  @GetMapping("/user-interactions")
  public ResponseEntity<List<InteractionExportDTO>> exportAllInteractions() {
    try {

    return ResponseEntity.ok(interactionService.exportAllInteractions());
    } catch (Exception e) {
      log.error(e);
    }
    return null;
  }

}
