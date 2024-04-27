package com.example.oauth2authenticationdemo.controller;


import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.service.CapsuleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/capsules")
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
}
