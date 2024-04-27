package com.example.oauth2authenticationdemo.config;

import com.example.oauth2authenticationdemo.enums.RegistrationSource;
import com.example.oauth2authenticationdemo.model.User;
import com.example.oauth2authenticationdemo.repository.UserRepository;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * Aceasta clasa se ocupa cu succesul autentificarii unui utilizator
 */
@Component
@RequiredArgsConstructor
public class OAutn2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    @Value("${frontend.url}")
    private String frontendUrl;

    @Autowired
    private UserRepository userRepository;



    /**
     * Această metodă este suprascrisă pentru a defini comportamentul dorit după ce autentificarea cu
     * OAuth2 are succes.
     */
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
        OAuth2AuthenticationToken oauth2Token = (OAuth2AuthenticationToken) authentication;
        OAuth2User oauth2User = oauth2Token.getPrincipal();
        Map<String, Object> attributes = oauth2User.getAttributes();
        String source = oauth2Token.getAuthorizedClientRegistrationId();


        if("github".equals(source)){

            String email = (String) attributes.getOrDefault("login", "");
           // Verifică dacă utilizatorul există deja în baza de date
            Optional<User> existingUser = userRepository.findByEmail(email);
            if (existingUser.isEmpty()) {
                // Utilizatorul nu există, așa că îl adăugăm în baza de date
                User newUser = new User(email);
                newUser.setSource(RegistrationSource.GITHUB);
                //newUser.setSource(RegistrationSource.valueOf(source));
                // Salvează noul utilizator în baza de date
                userRepository.save(newUser);
            }
            DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority("User")), attributes, "id");
            Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority("User"))
                    , oauth2Token.getAuthorizedClientRegistrationId());
            SecurityContextHolder.getContext().setAuthentication(securityAuth);

        } else if("google".equals(source)){
            String email = (String) attributes.getOrDefault("email", "");

            // Verifică dacă utilizatorul există deja în baza de date
            Optional<User> existingUser = userRepository.findByEmail(email);
            //String source = oauth2Token.getAuthorizedClientRegistrationId();
            if (existingUser.isEmpty()) {
                // Utilizatorul nu există, așa că îl adăugăm în baza de date
                User newUser = new User(email);
                newUser.setSource(RegistrationSource.GOOGLE);
                //newUser.setSource(RegistrationSource.valueOf(source));
                // Salvează noul utilizator în baza de date
                userRepository.save(newUser);
            }

            DefaultOAuth2User newUser = new DefaultOAuth2User(List.of(new SimpleGrantedAuthority("User")), attributes, "sub");
            Authentication securityAuth = new OAuth2AuthenticationToken(newUser, List.of(new SimpleGrantedAuthority("User"))
                    , oauth2Token.getAuthorizedClientRegistrationId());
            SecurityContextHolder.getContext().setAuthentication(securityAuth);

        }



        this.setAlwaysUseDefaultTargetUrl(true);
        this.setDefaultTargetUrl(frontendUrl);
        super.onAuthenticationSuccess(request, response, authentication);
    }
}
