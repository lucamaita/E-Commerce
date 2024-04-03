package com.SCAI.ecommerce.config;

import static org.springframework.security.config.Customizer.withDefaults;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.Collections;
// SecurityFilterChain: Definisce la catena di filtri di sicurezza personalizzata per l'applicazione.
//  Utilizza HttpSecurity per configurare la gestione delle sessioni come stateless,
//  il che è tipico per le API REST che utilizzano token per l'autenticazione invece di sessioni.
//  Specifica che le richieste agli endpoint sotto /api/** richiedono autenticazione,
//  mentre tutte le altre richieste sono permesse.
//  Inoltre, disabilita il CSRF (Cross-Site Request Forgery) per evitare attacchi, dato che l'applicazione è stateless,
//  e configura il CORS per permettere richieste da qualsiasi origine.
//  Infine, aggiunge un filtro personalizzato (JwtTokenValidator) prima del filtro di autenticazione di base per validare i token JWT.

// CorsConfigurationSource: Fornisce una configurazione CORS personalizzata che permette richieste da qualsiasi origine (*),
// con qualsiasi metodo HTTP e header. Questo è utile per consentire alle applicazioni client, c
// he potrebbero essere ospitate su domini diversi, di interagire con l'API.

// PasswordEncoder: Definisce un bean per l'encoding delle password utilizzando BCryptPasswordEncoder.
// Questo encoder è comunemente usato per la sua forza crittografica e per il fatto che include un sale automaticamente,
// rendendo ogni hash unico anche per password uguali.

@Configuration
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(Authorize -> Authorize.requestMatchers("/api/**")
                        .authenticated()
                        .anyRequest()
                        .permitAll())
                .addFilterBefore(new JwtTokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .formLogin(withDefaults());

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {

        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg=new CorsConfiguration();
                cfg.setAllowedOrigins(Collections.singletonList("*"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Collections.singletonList("*"));
                cfg.setMaxAge(3600L);
                return cfg;
            }
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
