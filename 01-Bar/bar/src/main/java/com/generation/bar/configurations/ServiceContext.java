package com.generation.bar.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.bar.services.BevandaService;
import com.generation.bar.services.SnackService;

@Configuration
public class ServiceContext {
    
    @Bean
    public BevandaService newBevandaService(){
        return new BevandaService();
    }

    @Bean
    public SnackService newSnackService(){
        return new SnackService();
    }
}
