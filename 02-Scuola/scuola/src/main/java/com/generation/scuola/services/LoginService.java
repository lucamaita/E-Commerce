package com.generation.scuola.services;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.scuola.dao.UserDAO;
import com.generation.scuola.entities.Persona;

public class LoginService {
    
    @Autowired
    private UserDAO userDAO;


    public Persona findUser(String username, String password){
        return userDAO.readFromUsernameAndPassword(username, password);
    }
}
