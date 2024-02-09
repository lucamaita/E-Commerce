package com.generation.scuola.configurations;

import java.sql.Date;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.scuola.entities.Classe;
import com.generation.scuola.entities.Dirigente;
import com.generation.scuola.entities.Docente;
import com.generation.scuola.entities.Studente;

@Configuration
public class EntitiesContext {
    
    @Bean
    @Scope("prototype")
    public Studente newStudente(Map<String, String> params){
        int id = Integer.parseInt(params.get("id"));
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        Date dataNascita = Date.valueOf(params.get("dataNascita"));
        String username = params.get("username");
        String password = params.get("password");

        return new Studente(id, nome, cognome, dataNascita, username, password);
    }

    @Bean
    @Scope("prototype")
    public Docente newDocente(Map<String, String> params){
        int id = Integer.parseInt(params.get("id"));
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        Date dataNascita = Date.valueOf(params.get("dataNascita"));
        String username = params.get("username");
        String password = params.get("password");

        return new Docente(id, nome, cognome, dataNascita, username, password);
    }

    @Bean
    @Scope("prototype")
    public Dirigente newDirigente(Map<String, String> params){
        int id = Integer.parseInt(params.get("id"));
        String nome = params.get("nome");
        String cognome = params.get("cognome");
        Date dataNascita = Date.valueOf(params.get("dataNascita"));
        String username = params.get("username");
        String password = params.get("password");

        return new Dirigente(id, nome, cognome, dataNascita, username, password);
    }

    @Bean
    @Scope("prototype")
    public Classe newClasse(Map<String, String> params){
        int id = Integer.parseInt(params.get("id"));
        String sezione = params.get("sezione");

        return new Classe(id, sezione);
    }
}
