package com.generation.bar.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


//I controller sono tutte quelle classi che definiscono al loro interno metodi in ascolto di richieste HTTP
//Ogni volta che arriva una richiesta HTTP da frontend un metodo dei controller risponde (viene invocato da spring)
//Le richieste HTTP sono principalmente di tipo GET e POST, le richieste GET solitamente vengono fatte per chiedere una risosa al backand e possono avere
//dei parametri visibili nell'url (esempio  di richiesta: http://localhost:8080/richiesta?paramatro1=valore1&parametro2=valore2)
//Le richieste POST invece vengono solitamente effetuate per passare dei dati al backend e questi datyi vengono passati tramite un pacchetto della richiesta detto payload o body
@Controller
public class AppController {
    

    @GetMapping("/home")
    public String home(){
        System.out.println("SONO NELLA RICHIESTA /home CIAOOOO");
        return "MainPage.html";
    }

}
