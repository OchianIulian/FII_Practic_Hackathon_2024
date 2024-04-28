package com.example.oauth2authenticationdemo.controller;


import com.example.oauth2authenticationdemo.model.Capsule;
import com.example.oauth2authenticationdemo.model.CapsuleIdList;
import com.example.oauth2authenticationdemo.model.User;
import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import com.example.oauth2authenticationdemo.repository.*;
import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.service.CapsuleService;
import com.example.oauth2authenticationdemo.utils.InMemoryMultipartFile;
import com.example.oauth2authenticationdemo.utils.ZipUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequiredArgsConstructor
@RequestMapping("/capsule")
public class CapsuleController {
    @Autowired
    private CapsuleRepository capsuleRepository;

    @Autowired
    private CapsuleService capsuleService;

    @Autowired
    private PictureRepository pictureRepository;

    @Autowired
    private VideoRepository videoRepository;

    @Autowired
    private TextFileRepository textFileRepository;

    @Autowired
    private CapsuledIdListRepository idListRepository;
    @Autowired
    private UserRepository userRepository;

    /**
     * Create a new post
     * @param capsuleRequest
     * @return
     */
    @PostMapping("/create-capsule")
    public ResponseEntity<String> createCapsule(@RequestBody CapsuleRequest capsuleRequest){
        return capsuleService.createCapsule(capsuleRequest);
    }

    @GetMapping("/get-post-by-id")
    public ResponseEntity<String> getCapsuleById(){
        return ResponseEntity.ok("postService.createPost(postRequest)");
    }


    @GetMapping("/get-capsule/{capsuleId}/{postToken}")
    public ResponseEntity<byte[]> getCapsule(@PathVariable Long capsuleId, @PathVariable Long postToken) throws IOException {
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

    @GetMapping("/get-own-capsules")
    public ResponseEntity<List<Capsule>> getOwnCapsules(@RequestParam Long userId){
        List<Capsule> capsules = capsuleService.getOwnCapsules(userId);
        return new ResponseEntity<>(capsules, HttpStatus.OK);
    }


    @GetMapping("/all_public")
    public ResponseEntity<List<Capsule>> getAllCapsules(){
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

    @GetMapping("/get-auth-uid")
    public Long getUid(){
        String email = "asd";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof DefaultOAuth2User) {
                email = ((DefaultOAuth2User) principal).getAttribute("email");
                // Utilizează variabila 'email' pentru a accesa adresa de email a utilizatorului
            }
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get().getId();
        }
        return -1L;
    }


}
