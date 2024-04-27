package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.Post;
import com.example.oauth2authenticationdemo.repository.PostRepository;
import com.example.oauth2authenticationdemo.request.PostRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class PostService {

    @Autowired
    PostRepository postRepository;

    public ResponseEntity<String> createPost(PostRequest postRequest) {
        Post post = new Post(postRequest.getMessage());
        postRepository.save(post);
        return ResponseEntity.ok("Post created successfully");
    }
}
