package com.example.oauth2authenticationdemo.request;

import com.example.oauth2authenticationdemo.model.capsule_content.Picture;
import com.example.oauth2authenticationdemo.model.capsule_content.TextFile;
import com.example.oauth2authenticationdemo.model.capsule_content.Video;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class CapsuleRequest {
    public final Long userId;
    public final String title;
    public final String description;
    public final List<TextFile> textFiles;
    public final List<Picture> pictures;
    public final List<Video> videos;
    public final LocalDateTime canBeOpenedAt;
}
