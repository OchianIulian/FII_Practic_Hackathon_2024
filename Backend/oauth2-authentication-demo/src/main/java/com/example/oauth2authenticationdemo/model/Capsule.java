package com.example.oauth2authenticationdemo.model;


import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Capsule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="user_id")
    private Long userId;
    private String title;
    private String description;
    @Column(name="txt_content")
    @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
    private List<TextFile> textFiles;
    @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
    private List<Picture> pictures;
    @OneToMany(mappedBy = "capsule", cascade = CascadeType.ALL)
    private List<Video> videos;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="can_be_opened_at")
    private LocalDateTime canBeOpenedAt;

    public Capsule(Long userId, String title, String description, List<TextFile> textFiles, List<Picture> pictures, List<Video> videos, LocalDateTime canBeOpenedAt) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.textFiles = textFiles;
        this.pictures = pictures;
        this.videos = videos;
        this.createdAt = LocalDateTime.now();
        this.canBeOpenedAt = canBeOpenedAt;
    }
}
