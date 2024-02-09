package com.generation.scuola.configurations;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.generation.scuola.dao.ClasseDAO;
import com.generation.scuola.dao.Database;
import com.generation.scuola.dao.DirigenteDAO;
import com.generation.scuola.dao.DocenteDAO;
import com.generation.scuola.dao.StudenteDAO;
import com.generation.scuola.dao.UserDAO;

@Configuration
public class DatabaseContext {
    
    @Bean
    public Database newDatabase(){
        return new Database("scuola");
    }

    @Bean
    public StudenteDAO newStudenteDAO(){
        return new StudenteDAO();
    }

    @Bean
    public DocenteDAO newDocenteDAO(){
        return new DocenteDAO();
    }

    @Bean
    public DirigenteDAO newDirigenteDAO(){
        return new DirigenteDAO();
    }

    @Bean
    public ClasseDAO newClasseDAO(){
        return new ClasseDAO();
    }

    @Bean
    public UserDAO newUserDAO(){
        return new UserDAO();
    }
}
