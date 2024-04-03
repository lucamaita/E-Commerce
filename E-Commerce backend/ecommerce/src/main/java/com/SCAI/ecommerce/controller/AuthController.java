package com.SCAI.ecommerce.controller;

import com.SCAI.ecommerce.repository.UserRepository;
import com.SCAI.ecommerce.request.LoginRequest;
import com.SCAI.ecommerce.response.AuthResponse;
import com.SCAI.ecommerce.service.CustomUserDetailsService;
import com.SCAI.ecommerce.config.JwtProvider;
import com.SCAI.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
// AuthController offre due endpoint principali: /signup e /signin,
// che permettono rispettivamente la registrazione e l'accesso degli utenti.
// AuthController facilita la registrazione e l'accesso degli utenti nell'applicazione,
// gestendo la creazione di nuovi account, l'autenticazione delle credenziali
// e la generazione di token JWT per gli utenti autenticati. Utilizza servizi ausiliari come
// UserRepository, CustomUserDetailsService, JwtProvider e PasswordEncoder
// per eseguire queste operazioni in modo sicuro e efficiente.
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CustomUserDetailsService customUserDetailService;

    @Autowired
    private JwtProvider jwtProvider;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/signup")
    public AuthResponse createUser(@RequestBody User user) throws Exception {

        String email = user.getEmail();
        String password = user.getPassword();
        String fullName = user.getFullName();

        User isExistEmail = userRepository.findByEmail(email);
        if (isExistEmail != null) {
            throw new Exception("Email is already used with another account");
        }

        User createdUser = new User();
        createdUser.setEmail(email);
        createdUser.setPassword(passwordEncoder.encode(password));
        createdUser.setFullName(fullName);
        createdUser.setRole(User.Role.GUEST);

        User savedUser = userRepository.save(createdUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(email, password);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);
        authResponse.setMessage("signup success");

        return authResponse;
    }

    @PostMapping("/signin")
    public AuthResponse signinHandler(@RequestBody LoginRequest loginRequest) {
        String username = loginRequest.getEmail();
        String password = loginRequest.getPassword();

        Authentication authentication = authenticate(username, password);
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String token = jwtProvider.generateToken(authentication);

        AuthResponse authResponse = new AuthResponse();

        authResponse.setJwt(token);
        authResponse.setMessage("login success");

        return authResponse;

    }

    private Authentication authenticate(String username, String password) {
        UserDetails userDetail = customUserDetailService.loadUserByUsername(username);

        if (userDetail == null) {
            throw new BadCredentialsException("User not found");
        }
        if (!passwordEncoder.matches(password, userDetail.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }

        return new UsernamePasswordAuthenticationToken(userDetail, null, userDetail.getAuthorities());
    }

}
