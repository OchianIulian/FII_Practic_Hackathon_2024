package com.example.oauth2authenticationdemo.request;

import lombok.*;

@Getter
@EqualsAndHashCode
@AllArgsConstructor
@NoArgsConstructor(force=true)
@ToString
public class TextFileRequest {
    public final String message;
}
