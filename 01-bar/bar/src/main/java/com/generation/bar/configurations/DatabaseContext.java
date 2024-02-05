package com.generation.bar.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.bar.dao.BevandaDAO;
import com.generation.bar.dao.Database;
import com.generation.bar.dao.SnackDAO;


//@Configuration Comunica a spring che questa classe è una classe di configurazione dove andremo a definire
//come istanziare alcune dipendenze di cui avremo bisogno nell'applicativo.
//Le classi di configurazione sono delle vere e proprie factory.
@Configuration
public class DatabaseContext {
    
    //@Bean serve a spring per conoscere la dipendenza da utilizzare.
    //Tutto ciò che è contrassegnato con @Bean normalmente è singleton
    @Bean
    public Database newDatabase(){
        return new Database("bar");
    }

    @Bean
    public SnackDAO newSnackDAO(){
        return new SnackDAO();
    }

    @Bean
    public BevandaDAO newBevandaDAO(){
        return new BevandaDAO();
    }
}
