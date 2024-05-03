package com.example.oauth2authenticationdemo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CapsuleIdList {
    @jakarta.persistence.Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long id;

    @Column(name = "capsule_id")
    private Long capsuleId;

    @Column(name = "user_id")
    private Long userId;

    @ElementCollection
    private List<Long> listOfIds = new ArrayList<>();


    public void addIdToList(Long id){
        listOfIds.add(id);
    }
}
