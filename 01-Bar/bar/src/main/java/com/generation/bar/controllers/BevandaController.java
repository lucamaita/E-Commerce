package com.generation.bar.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.generation.bar.entities.Bevanda;
import com.generation.bar.entities.BevandaForm;
import com.generation.bar.services.BevandaService;

@Controller
public class BevandaController {
    
    @Autowired
    private BevandaService bevandaService;


    @GetMapping("/allBevande")
    public String allBevande(Model model){
        List<Bevanda> listaBevande = bevandaService.findAll();
        model.addAttribute("bevande", listaBevande);
        return "Bevande.html";
    }


    //http:localhost:8080/dettaglioBevande?id=[ID]
    @GetMapping("/dettaglioBevanda")
    public String dettaglio(Model model, @RequestParam(name = "id") int id){
        Bevanda b = bevandaService.findById(id);
        model.addAttribute("bevanda", b);
        return "dettaglioBevanda.html";
    }

    /* 
    /dettaglioBevanda/1
    /dettaglioBevanda/2
    /dettaglioBevanda/3
    /dettaglioBevanda/...
    /dettaglioBevanda/N
    */
    @GetMapping("/dettaglioBevanda/{id}")
    public String dettaglio1(Model model, @PathVariable(name = "id") int id) {
        Bevanda b = bevandaService.findById(id);
        model.addAttribute("bevanda", b);
        return "dettaglioBevanda.html";
    }

    // @GetMapping("/inserisciBevanda")
    // public String inserisciBevanda(@RequestParam(name = "nome") String nome, @RequestParam("prezzo") double prezzo){
    //     System.out.println("Nome bevanda: " + nome);
    //     System.out.println("Prezzo bevanda: " + prezzo);
    //     return "Bevande.html";
    // }

    @PostMapping("/inserisciBevanda")
    public String inserisciBevanda(@RequestParam Map<String, String> params){
        bevandaService.insertBevanda(params);        
        return "redirect:/allBevande";
    }

    @PostMapping("/modificaBevanda")
    public String modificaBevanda(@RequestParam Map<String, String> params){
        bevandaService.modificaBevanda(params);        
        return "redirect:/allBevande";
    }

    @GetMapping("/eliminaBevanda")
    public String eliminaBevanda(@RequestParam(name = "id") int id){
        bevandaService.eliminaBevanda(id);  
        return "redirect:/allBevande";
    }


    // @PostMapping("/inserisciBevanda")
    // public String inserisciBevanda(BevandaForm params){
    //     System.out.println("Nome: " + params.getNome());     
    //     System.out.println("Prezzo: " + params.getPrezzo());     
    //     return "redirect:/allBevande";
    // }
}
