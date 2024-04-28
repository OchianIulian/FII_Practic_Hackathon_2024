package com.example.oauth2authenticationdemo.model;

import com.example.oauth2authenticationdemo.enums.RegistrationSource;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@EqualsAndHashCode
@NoArgsConstructor
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;

    @Column(name = "source")
    private RegistrationSource source;

    public User(String email) {
        this.email = email;
    }
}
