package com.example.oauth2authenticationdemo.repository;

import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
@Transactional
public interface PictureRepository extends JpaRepository<Picture, Long> {
}
