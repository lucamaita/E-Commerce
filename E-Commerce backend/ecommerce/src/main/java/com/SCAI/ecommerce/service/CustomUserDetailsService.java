package com.SCAI.ecommerce.service;

import java.util.ArrayList;
import java.util.List;

import com.SCAI.ecommerce.repository.UserRepository;
import com.SCAI.ecommerce.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
//Questo blocco di codice definisce CustomUserDetailsService,
// un servizio che implementa l'interfaccia UserDetailsService di Spring Security
// per personalizzare il processo di autenticazione degli utenti.
// Il servizio utilizza UserRepository per recuperare gli utenti dal database basandosi sull'email.
// Nel metodo loadUserByUsername, viene cercato un utente tramite la sua email:
// se l'utente non viene trovato, viene lanciata un'eccezione UsernameNotFoundException.
// Se l'utente esiste, viene creato e restituito un oggetto UserDetails di Spring Security,
// che include l'email, la password dell'utente e una lista vuota di GrantedAuthority,
// indicando che non sono stati specificati ruoli o autorizzazioni particolari per l'utente.
// Questo meccanismo è fondamentale per integrare l'autenticazione basata su Spring Security in un'applicazione.
@Service
public class CustomUserDetailsService implements UserDetailsService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.findByEmail(email);

        if( user == null) {
            throw new UsernameNotFoundException("User not found with email " + email);
        }

        List<GrantedAuthority> authorities = new ArrayList<>();

        return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), authorities);
    }

}
