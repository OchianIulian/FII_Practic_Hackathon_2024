package com.example.oauth2authenticationdemo.controller;


import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import com.example.oauth2authenticationdemo.service.CapsuleService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("post")
public class PostController {
    @Autowired
    private CapsuleService capsuleService;

    /**
     * Create a new post
     * @param postRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody CapsuleRequest postRequest){
        return capsuleService.createPost(postRequest);
    }

    @GetMapping("/get-post-by-id")
    public ResponseEntity<String> getPostById(){
        return ResponseEntity.ok("postService.createPost(postRequest)");
    }
}
