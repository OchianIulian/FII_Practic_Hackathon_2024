package com.example.oauth2authenticationdemo.repository;

import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VideoRepository extends JpaRepository<Video, Long> {
}
