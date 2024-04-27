package com.example.oauth2authenticationdemo.model.capsule_content;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "pictures")
@Getter
@Setter
@NoArgsConstructor
public class Picture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fileName;

    @Column(nullable = false)
    private byte[] data;

    public Picture(String fileName, byte[] data) {
        this.fileName = fileName;
        this.data = data;
    }
}
