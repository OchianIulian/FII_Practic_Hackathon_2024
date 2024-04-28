package com.example.oauth2authenticationdemo.repository;

import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Transactional
public interface VideoRepository extends JpaRepository<Video, Long> {
    List<Video> findByCapsuleId(Long id);
}
