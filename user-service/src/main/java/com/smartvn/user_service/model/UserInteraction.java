package com.smartvn.user_service.model;

import java.time.Instant;

import com.smartvn.user_service.enums.InteractionType;

import org.hibernate.annotations.CreationTimestamp;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EntityListeners(AuditingEntityListener.class)
@Entity
@Table(name = "user_interactions")
public class UserInteraction {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "user_id")
  private Long userId;

  @Column(name = "product_id", nullable = false)
  private Long productId;

  @Column(name = "interaction_type" )
  @Enumerated(EnumType.STRING)
  private InteractionType interactionType;

  @CreationTimestamp
  @Column(name = "created_at", updatable = false)
  private Instant createdAt;

  // Constructors, getters, and setters

  // Getters and setters for user, product, weight, and createdAt

  // toString() method

  // Override equals() and hashCode() methods
}
