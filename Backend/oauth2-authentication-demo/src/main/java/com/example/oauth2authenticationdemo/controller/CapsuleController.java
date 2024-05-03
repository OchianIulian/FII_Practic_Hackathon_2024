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
import com.example.oauth2authenticationdemo.service.UserService;
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
    private UserService userService;

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
     * @param
     * @return
     */
//    @PostMapping("/create-capsule")
//    public ResponseEntity<String> createCapsule(@RequestBody CapsuleRequest capsuleRequest){
//        return capsuleService.createCapsule(capsuleRequest);
//    }

    @GetMapping("/get-post-by-id")
    public ResponseEntity<String> getCapsuleById(){
        return ResponseEntity.ok("postService.createPost(postRequest)");
    }


    @GetMapping("/get-capsule/{capsuleId}/{postToken}")
    public ResponseEntity<byte[]> getCapsule(@PathVariable Long capsuleId, @PathVariable Long postToken) throws IOException {
        return capsuleService.zipCapsule(capsuleId, postToken);
    }

    @GetMapping("/get-own-capsules")
    public ResponseEntity<List<Capsule>> getOwnCapsules(@RequestParam Long userId){
        List<Capsule> capsules = capsuleService.getOwnCapsules(userId);
        return new ResponseEntity<>(capsules, HttpStatus.OK);
    }


    @GetMapping("/all_public")
    public ResponseEntity<List<Capsule>> getAllCapsules(){
        return capsuleService.getAllCapsules();
    }

    @GetMapping("/get-auth-uid")
    public Long getUid(){
        return userService.getUserId();
    }


}
