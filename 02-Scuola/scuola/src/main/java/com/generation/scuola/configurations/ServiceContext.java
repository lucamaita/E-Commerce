package com.generation.scuola.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.scuola.services.LoginService;

@Configuration
public class ServiceContext {
    @Bean
    public LoginService newLoginService(){
        return new LoginService();
    }

}
