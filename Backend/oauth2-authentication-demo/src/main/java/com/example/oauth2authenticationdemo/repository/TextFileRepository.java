package com.example.oauth2authenticationdemo.repository;

import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Text;

import java.util.List;

@Repository
@Transactional
public interface TextFileRepository extends JpaRepository<TextFile, Long> {
    List<TextFile> findByCapsuleId(Long id);
}
