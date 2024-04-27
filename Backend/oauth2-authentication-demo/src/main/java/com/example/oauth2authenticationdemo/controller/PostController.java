package com.example.oauth2authenticationdemo.controller;


import com.example.oauth2authenticationdemo.request.PostRequest;
import com.example.oauth2authenticationdemo.service.PostService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("post")
public class PostController {
    @Autowired
    private PostService postService;

    /**
     * Create a new post
     * @param postRequest
     * @return
     */
    @PostMapping("/create")
    public ResponseEntity<String> createPost(@RequestBody PostRequest postRequest){
        return postService.createPost(postRequest);
    }

    @GetMapping("/get-post-by-id")
    public ResponseEntity<String> getPostById(){
        return ResponseEntity.ok("postService.createPost(postRequest)");
    }
}
