package ru.zagir.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.zagir.forms.RegistrationForm;
import ru.zagir.models.User;
import ru.zagir.repositories.UserRepository;
import ru.zagir.models.Role;
import ru.zagir.models.State;

import java.util.Optional;

@Service
public class RegistrationService {
    @Autowired
    UserRepository userRepository;
    @Autowired
    PasswordEncoder passwordEncoder;
    @Autowired
    private UserDetailsService userDetailsService;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private FilesStorageService storageService;

    public void registration(RegistrationForm form) {
        userRepository.save(User.builder()
                .eMail(form.getEMail())
                .firstName(Optional.of(form.getFirstName()).orElse(form.getLogin()))
                .lastName(Optional.of(form.getLastName()).orElse(""))
                .login(form.getLogin())
                .password(passwordEncoder.encode(form.getPassword()))
                .role(Role.USER)
                .state(State.UNCONFIRMED)
                .build());
        try {
        storageService.saveImage(autoLogin(form.getLogin(),form.getPassword()), form.getImage());
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public Authentication autoLogin(String username, String password) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, password, userDetails.getAuthorities());
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            return usernamePasswordAuthenticationToken;
        }
        return null;
    }
}
