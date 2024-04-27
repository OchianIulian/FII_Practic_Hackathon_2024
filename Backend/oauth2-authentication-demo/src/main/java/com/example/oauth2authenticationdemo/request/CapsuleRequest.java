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
    public final List<TextFileRequest> textFiles;
    public final List<PictureRequest> pictures;
    public final List<VideoRequest> videos;
    public final LocalDateTime canBeOpenedAt;
}
