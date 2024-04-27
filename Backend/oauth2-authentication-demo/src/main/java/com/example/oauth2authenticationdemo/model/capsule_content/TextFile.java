package com.example.oauth2authenticationdemo.model.capsule_content;

import com.example.oauth2authenticationdemo.model.Capsule;
import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "text_file")
public class TextFile {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String message;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "capsule_id", nullable = false)
    private Capsule capsule;

    public TextFile(String message) {
        this.message = message;
    }
}
