package com.example.oauth2authenticationdemo.request;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force = true)
@ToString
public class CapsuleRequest {
    public final Long userId;
    public final String title;
    public final String description;
    public final String txtContent;
    public final List<Image> images;
    public final List<Video> videos;
    public final LocalDateTime canBeOpenedAt;
}
