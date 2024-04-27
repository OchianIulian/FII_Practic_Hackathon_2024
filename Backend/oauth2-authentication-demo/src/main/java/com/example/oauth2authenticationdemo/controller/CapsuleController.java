package com.example.oauth2authenticationdemo.controller;


import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.service.CapsuleService;
import com.example.oauth2authenticationdemo.utils.ZipUtil;
import lombok.AllArgsConstructor;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/capsule")
public class CapsuleController {
    @Autowired
    private CapsuleService capsuleService;

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

    @GetMapping("/upload")
    public ResponseEntity<byte[]> uploadFiles(
            @RequestParam(value = "videos", required = false) List<MultipartFile> videos,
            @RequestParam(value = "images", required = false) List<MultipartFile> images,
            @RequestParam(value = "textFiles", required = false) List<MultipartFile> textFiles) throws IOException {

        if (videos != null) {
            for (MultipartFile video : videos) {
                // Procesați fiecare fișier video
                String filename = video.getOriginalFilename();
                System.out.println("Video file name: " + filename);
            }
        } else {
            System.out.println("No video files uploaded");
        }

        if (images != null) {
            for (MultipartFile image : images) {
                // Procesați fiecare fișier imagine
                String filename = image.getOriginalFilename();
                System.out.println("Image file name: " + filename);
            }
        } else {
            System.out.println("No image files uploaded");
        }

        if (textFiles != null) {
            for (MultipartFile textFile : textFiles) {
                // Procesați fiecare fișier text
                String filename = textFile.getOriginalFilename();
                System.out.println("Text file name: " + filename);
            }
        } else {
            System.out.println("No text files uploaded");
        }


        File zipFile = ZipUtil.createZipFile(videos, images, textFiles);
        //return zipFile;
        byte[] zipBytes = FileUtils.readFileToByteArray(zipFile);
        return ResponseEntity.ok()
                .header("Content-Disposition", "attachment; filename=files.zip")
                .body(zipBytes);
        //return ResponseEntity.ok("Created");
    }
}
