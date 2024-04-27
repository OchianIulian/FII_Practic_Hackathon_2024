package com.example.oauth2authenticationdemo.model.capsule_content;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "text_files")
public class TextFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    public TextFile(String message) {
        this.message = message;
    }
}
