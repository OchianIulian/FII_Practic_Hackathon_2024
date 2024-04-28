package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.Capsule;
import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import com.example.oauth2authenticationdemo.repository.CapsuleRepository;
import com.example.oauth2authenticationdemo.repository.PictureRepository;
import com.example.oauth2authenticationdemo.repository.TextFileRepository;
import com.example.oauth2authenticationdemo.repository.VideoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class FormDataService {
    @Autowired
    CapsuleRepository capsuleRepository;

    @Autowired
    TextFileRepository textFileRepository;

    @Autowired
    PictureRepository pictureRepository;

    @Autowired
    VideoRepository videoRepository;

    public ResponseEntity<String> saveFormData(Long userId, String title, String description, List<MultipartFile> textFiles, List<MultipartFile> pictures, List<MultipartFile> videos, LocalDateTime canBeOpenedAt, boolean isPrivate) {
        try {
            Capsule capsule = new Capsule(userId, title, description, canBeOpenedAt, isPrivate);
            capsuleRepository.save(capsule);
            //List<TextFile> textFilesEntities = new ArrayList<>();
            for (MultipartFile textFile : textFiles) {
                try {
                    byte[] data = textFile.getBytes();
                    String filename = textFile.getOriginalFilename();
                    TextFile textFileEntity = new TextFile();
//                    String content = new String(data);
//                    textFileEntity.setMessage(content);
                    textFileEntity.setFileName(filename);
                    textFileEntity.setData(data);
                    textFileEntity.setCapsule(capsule);
                    textFileRepository.save(textFileEntity);
                    //textFilesEntities.add(textFileEntity);
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Failed to read txt file");
                }
            }
            //capsule.setTextFiles(textFilesEntities);
            //List<Picture> pictureEntities = new ArrayList<>();
            for (MultipartFile pictureFile : pictures) {
                try {
                    byte[] data = pictureFile.getBytes();
                    String filename = pictureFile.getOriginalFilename();
                    Picture pictureEntity = new Picture();
                    pictureEntity.setData(data);
                    pictureEntity.setFileName(filename);
                    pictureEntity.setCapsule(capsule);
                    pictureRepository.save(pictureEntity);
                    //pictureEntities.add(pictureEntity);
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Failed to read picture file");
                }
            }
            //capsule.setPictures(pictureEntities);
            //List<Video> videoEntities = new ArrayList<>();
            for (MultipartFile videoFile : videos) {
                try {
                    byte[] data = videoFile.getBytes();
                    String filename = videoFile.getOriginalFilename();
                    Video videoEntity = new Video();
                    videoEntity.setData(data);
                    videoEntity.setFileName(filename);
                    videoEntity.setCapsule(capsule);
                    videoRepository.save(videoEntity);
                    //videoEntities.add(videoEntity);
                } catch (IOException e) {
                    return ResponseEntity.badRequest().body("Failed to read video file");
                }
            }
            //capsule.setVideos(videoEntities);
            return ResponseEntity.ok("Capsule created successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Failed to create Capsule: " + e.getMessage());
        }
    }
}
