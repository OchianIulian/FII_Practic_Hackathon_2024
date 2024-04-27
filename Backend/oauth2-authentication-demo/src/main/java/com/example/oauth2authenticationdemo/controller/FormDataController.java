package com.example.oauth2authenticationdemo.controller;

import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.service.FormDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api")
public class FormDataController {
    @Autowired
    private FormDataService formDataService;
    @PostMapping("/saveData")
    public ResponseEntity<?> saveFormData(@RequestParam("userId") Long userId,
                                          @RequestParam("title") String title,
                                          @RequestParam("description") String description,
                                          @RequestParam("textFiles") List<MultipartFile> textFiles,
                                          @RequestParam("pictures") List<MultipartFile> pictures,
                                          @RequestParam("videos") List<MultipartFile> videos,
                                          @RequestParam("canBeOpenedAt") LocalDateTime canBeOpenedAt,
                                          @RequestParam("isPrivate") boolean isPrivate) {
        return formDataService.saveFormData(userId, title, description, textFiles, pictures, videos, canBeOpenedAt, isPrivate);
    }

//    @PostMapping("/create-capsule")
//    public ResponseEntity<String> createCapsule(@RequestBody CapsuleRequest capsuleRequest){
//        return capsuleService.createCapsule(capsuleRequest);
//    }
}
