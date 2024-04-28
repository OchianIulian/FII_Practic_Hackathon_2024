package com.example.oauth2authenticationdemo.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force=true)
@ToString
public class VideoRequest {
    public final String fileName;
    public final byte[] data;
}
