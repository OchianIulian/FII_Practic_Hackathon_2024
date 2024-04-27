package com.example.oauth2authenticationdemo.model;


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
    private String txtContent;
    private List<Image> images;
    private List<Video> videos;
    @Column(name="created_at")
    private LocalDateTime createdAt;
    @Column(name="can_be_opened_at")
    private LocalDateTime canBeOpenedAt;

    public Capsule(Long userId, String title, String description, String txtContent, List<Image> images, List<Video> videos, LocalDateTime canBeOpenedAt) {
        this.userId = userId;
        this.title = title;
        this.description = description;
        this.txtContent = txtContent;
        this.images = images;
        this.videos = videos;
        this.createdAt = LocalDateTime.now();
        this.canBeOpenedAt = canBeOpenedAt;
    }
}
