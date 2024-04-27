package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.Capsule;
import com.example.oauth2authenticationdemo.repository.CapsuleRepository;
import com.example.oauth2authenticationdemo.request.CapsuleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CapsuleService {

    @Autowired
    CapsuleRepository capsuleRepository;

//    public ResponseEntity<String> createPost(CapsuleRequest capsuleRequest) {
//        Capsule capsule = new Capsule(capsuleRequest.getMessage());
//        capsuleRepository.save(capsule);
//        return ResponseEntity.ok("Capsule created successfully");
//    }
}
