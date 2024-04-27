package com.example.oauth2authenticationdemo.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force=true)
@ToString
public class PictureRequest {
    public final String fileName;
    public final byte[] data;
}
