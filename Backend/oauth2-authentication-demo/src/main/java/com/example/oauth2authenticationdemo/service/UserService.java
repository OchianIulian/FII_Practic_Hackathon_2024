package com.example.oauth2authenticationdemo.service;

import com.example.oauth2authenticationdemo.model.User;
import com.example.oauth2authenticationdemo.repository.UserRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@Service
@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Long getUserId() {
        String email = "asd";
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            if (principal instanceof DefaultOAuth2User) {
                email = ((DefaultOAuth2User) principal).getAttribute("email");
                // Utilizează variabila 'email' pentru a accesa adresa de email a utilizatorului
            }
        }

        Optional<User> user = userRepository.findByEmail(email);
        if(user.isPresent()){
            return user.get().getId();
        }
        return -1L;
    }
}
