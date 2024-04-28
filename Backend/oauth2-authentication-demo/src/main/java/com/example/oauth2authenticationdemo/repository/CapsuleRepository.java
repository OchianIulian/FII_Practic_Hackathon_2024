package com.example.oauth2authenticationdemo.repository;


import com.example.oauth2authenticationdemo.model.Capsule;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CapsuleRepository extends JpaRepository<Capsule, Long> {

    Optional<Capsule> findCapsuleById(Long id);
    List<Capsule> findByUserId(Long userId);

    @Query("SELECT c FROM Capsule c WHERE c.isPrivate = false")
    List<Capsule> getAllPublicCapsules();
}
