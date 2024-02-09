package com.generation.scuola.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.generation.scuola.entities.Dirigente;

import jakarta.servlet.http.HttpSession;

@Controller
public class PersonaController {
    
    @GetMapping("/areaStudenti")
    public String areaStudenti(){
        return "areaStudente.html";
    }

    @GetMapping("/areaDocenti")
    public String areaDocenti(){
        return "areaDocente.html";
    }

    @GetMapping("/areaDirigenti")
    public String areaDirigenti(HttpSession session, Model model){

        if(session.getAttribute("LOGIN") != null){
            if(session.getAttribute("LOGIN").toString().equals("OK")){
                if(session.getAttribute("RUOLO").toString().equals("DIRIGENTE")){
                    Dirigente d = (Dirigente)session.getAttribute("ENTITY");
                    model.addAttribute("dirigente", d);
                    
                    return "areaDirigente.html";
                }




            }
        }


        return "redirect:/loginPage";

        
    }


}
