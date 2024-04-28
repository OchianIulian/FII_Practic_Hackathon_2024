package com.example.oauth2authenticationdemo.repository;

import com.example.oauth2authenticationdemo.model.CapsuleIdList;
import com.example.oauth2authenticationdemo.model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
@Transactional
public interface CapsuledIdListRepository extends JpaRepository<CapsuleIdList, Long> {

    Optional<CapsuleIdList> findByUserId(Long userId);
}
