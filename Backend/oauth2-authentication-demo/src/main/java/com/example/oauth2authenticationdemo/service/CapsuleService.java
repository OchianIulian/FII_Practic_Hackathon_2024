package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.Capsule;
import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import com.example.oauth2authenticationdemo.repository.CapsuleRepository;
import com.example.oauth2authenticationdemo.repository.PictureRepository;
import com.example.oauth2authenticationdemo.repository.TextFileRepository;
import com.example.oauth2authenticationdemo.repository.VideoRepository;
import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.request.PictureRequest;
import com.example.oauth2authenticationdemo.request.TextFileRequest;
import com.example.oauth2authenticationdemo.request.VideoRequest;
import com.example.oauth2authenticationdemo.utils.InMemoryMultipartFile;
import com.example.oauth2authenticationdemo.utils.ZipUtil;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CapsuleService {

    @Autowired
    private CapsuleRepository capsuleRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private TextFileRepository textFileRepository;

//    public ResponseEntity<String> createCapsule(CapsuleRequest capsuleRequest) {
//        try{
//            Capsule capsule = new Capsule(capsuleRequest.getUserId(), capsuleRequest.getTitle(), capsuleRequest.getDescription(), capsuleRequest.getCanBeOpenedAt(), capsuleRequest.isPrivate());
//            capsuleRepository.save(capsule);
//            List<TextFile> textFiles = new ArrayList<>();
//            for(TextFileRequest textFileRequest : capsuleRequest.getTextFiles()){
//                TextFile textFile = new TextFile(textFileRequest.getMessage());
//                textFile.setCapsule(capsule);
//                textFiles.add(textFile);
//            }
//            //capsule.setTextFiles(textFiles);
//            List<Picture> pictures = new ArrayList<>();
//            for(PictureRequest pictureRequest : capsuleRequest.getPictures()){
//                Picture picture = new Picture(pictureRequest.getFileName(), pictureRequest.getData());
//                picture.setCapsule(capsule);
//                pictures.add(picture);
//            }
//            //capsule.setPictures(pictures);
//            List<Video> videos = new ArrayList<>();
//            for(VideoRequest videoRequest : capsuleRequest.getVideos()){
//                Video video = new Video(videoRequest.getFileName(), videoRequest.getData());
//                video.setCapsule(capsule);
//                videos.add(video);
//            }
//            //capsule.setVideos(videos);
//            return ResponseEntity.ok("Capsule created successfully");
//        } catch(Exception e){
//            return ResponseEntity.badRequest().body("Failed to create Capsule: "+e.getMessage());
//        }
//    }

    public List<Capsule> getOwnCapsules(Long userId){
        return capsuleRepository.findByUserId(userId);
    }

    public ResponseEntity<byte[]> zipCapsule(Long capsuleId, Long postToken) throws IOException {
        List<MultipartFile> videos = new ArrayList<>();
        List<MultipartFile> images = new ArrayList<>();
        List<MultipartFile> textFiles = new ArrayList<>();


        Optional<Capsule> capsule = capsuleRepository.findCapsuleById(capsuleId);
        if(capsule.isPresent()){
            List<Video> videosList = videoRepository.findByCapsuleId(capsuleId);
            for(Video video : videosList){
                videos.add(new InMemoryMultipartFile(video.getFileName(), video.getFileName(), video.getData()));
            }

            List<Picture> pictureList = pictureRepository.findByCapsuleId(capsuleId);
            for(Picture picture : pictureList){
                images.add(new InMemoryMultipartFile(picture.getFileName(), picture.getFileName(), picture.getData()));
            }

            List<TextFile> textFileList = textFileRepository.findByCapsuleId(capsuleId);
            for(TextFile textFile : textFileList){
                textFiles.add(new InMemoryMultipartFile(textFile.getFileName(), textFile.getFileName(), textFile.getData()));
            }
        }

        File zipFile = ZipUtil.createZipFile(videos, images, textFiles);
        byte[] zipBytes = FileUtils.readFileToByteArray(zipFile);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=files.zip")
                .body(zipBytes);

    }

    public ResponseEntity<List<Capsule>> getAllCapsules() {
        List<Capsule> capsules = capsuleRepository.getAllPublicCapsules();
        if (capsules.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        LocalDateTime date1 = LocalDateTime.now();


        List<Capsule> capsuleList = new ArrayList<>();
        for(Capsule capsule : capsules){
            LocalDateTime date2 = capsule.getCanBeOpenedAt();
            if(date1.isAfter(date2)){
                capsule.setUnlocked(true);
            }

            if(!capsule.isPrivate()) {
                capsuleList.add(capsule);
            }
        }

        return ResponseEntity.ok(capsuleList);
    }
}
