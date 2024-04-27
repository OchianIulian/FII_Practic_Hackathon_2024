package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.Capsule;
import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import com.example.oauth2authenticationdemo.repository.CapsuleRepository;
import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.request.PictureRequest;
import com.example.oauth2authenticationdemo.request.TextFileRequest;
import com.example.oauth2authenticationdemo.request.VideoRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CapsuleService {

    @Autowired
    CapsuleRepository capsuleRepository;

    public ResponseEntity<String> createCapsule(CapsuleRequest capsuleRequest) {
        try{
            Capsule capsule = new Capsule(capsuleRequest.getUserId(), capsuleRequest.getTitle(), capsuleRequest.getDescription(), capsuleRequest.getCanBeOpenedAt(), capsuleRequest.isPrivate());
            capsuleRepository.save(capsule);
            List<TextFile> textFiles = new ArrayList<>();
            for(TextFileRequest textFileRequest : capsuleRequest.getTextFiles()){
                TextFile textFile = new TextFile(textFileRequest.getMessage());
                textFile.setCapsule(capsule);
                textFiles.add(textFile);
            }
            //capsule.setTextFiles(textFiles);
            List<Picture> pictures = new ArrayList<>();
            for(PictureRequest pictureRequest : capsuleRequest.getPictures()){
                Picture picture = new Picture(pictureRequest.getFileName(), pictureRequest.getData());
                picture.setCapsule(capsule);
                pictures.add(picture);
            }
            //capsule.setPictures(pictures);
            List<Video> videos = new ArrayList<>();
            for(VideoRequest videoRequest : capsuleRequest.getVideos()){
                Video video = new Video(videoRequest.getFileName(), videoRequest.getData());
                video.setCapsule(capsule);
                videos.add(video);
            }
            //capsule.setVideos(videos);
            return ResponseEntity.ok("Capsule created successfully");
        } catch(Exception e){
            return ResponseEntity.badRequest().body("Failed to create Capsule: "+e.getMessage());
        }
    }

    public List<Capsule> getOwnCapsules(Long userId){
        return capsuleRepository.findByUserId(userId);
    }
}
