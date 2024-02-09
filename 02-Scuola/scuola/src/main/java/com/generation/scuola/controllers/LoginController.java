package com.generation.scuola.controllers;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.scuola.entities.Dirigente;
import com.generation.scuola.entities.Docente;
import com.generation.scuola.entities.Persona;
import com.generation.scuola.entities.Studente;
import com.generation.scuola.services.LoginService;

import jakarta.servlet.http.HttpSession;

@Controller
public class LoginController {
    
    @Autowired
    private LoginService loginService;


    @GetMapping("/loginPage")
    public String loginPage(HttpSession session){
        if(session.getAttribute("LOGIN") != null){
            if(session.getAttribute("LOGIN").toString().equals("OK")){
                Persona p = (Persona)session.getAttribute("ENTITY");
                if(p instanceof Studente){
                    return "redirect:/areaStudenti";
                }
                else if(p instanceof Docente){
                    return "redirect:/areaDocenti";
                }
                else if(p instanceof Dirigente){
                    return "redirect:/areaDirigenti";
                }
            }
        }
        

        return "loginPage.html";
        

        
    }


    //L'HttpSession è un Bean di spring come il Model, che funziona come il model (cioè può immagazinare delle informazione come oggetti, stringhe e tutto ciò che vogliamo)
    //ma queste informazioni a differenza del Model persistono tra le varie richieste e pagine, in questo modo io posso inserire delle informazioni da una richiesta e controllarle da un'altra
    @PostMapping("/login")
    public String login(@RequestParam Map<String, String> params, HttpSession session){
        Persona p = loginService.findUser(params.get("username"), params.get("password"));

        if(p instanceof Studente){
            session.setAttribute("LOGIN", "OK");
            session.setAttribute("RUOLO", "STUDENTE");
            session.setAttribute("ENTITY", p);
            return "redirect:/areaStudenti";
        }
        else if(p instanceof Docente){
            session.setAttribute("LOGIN", "OK");
            session.setAttribute("RUOLO", "DOCENTE");
            session.setAttribute("ENTITY", p);
            return "redirect:/areaDocenti";
        }
        else if(p instanceof Dirigente){
            session.setAttribute("LOGIN", "OK");
            session.setAttribute("RUOLO", "DIRIGENTE");
            session.setAttribute("ENTITY", p);
            return "redirect:/areaDirigenti";
        }
        else{
            //Login errato
            return "redirect:/errorlogin";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.invalidate();
        return "redirect:/home";
    }


    @GetMapping("/errorlogin")
    public String erroreLogin(){

        return "ErroreLogin.html";
    }
}
