package com.smartvn.user_service.repository;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

import com.smartvn.user_service.model.UserInteraction;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserInteractionRepository extends JpaRepository<UserInteraction, Long> {

@Query("""
    SELECT ui
    FROM UserInteraction ui
    WHERE ui.userId = :userId
      AND ui.productId = :productId
    ORDER BY ui.createdAt DESC
""")
UserInteraction findTopByUserIdAndProductIdOrderByCreatedAtDesc(
        @Param("userId") Long userId,
        @Param("productId") Long productId);

  @Query("SELECT ui FROM UserInteraction ui ORDER BY ui.createdAt ASC")
  List<UserInteraction> findAllByOrderByCreatedAtAsc();


  Optional<UserInteraction> findByUserIdAndProductIdOrderByCreatedAtDesc(Long userId, Long productId);
}
