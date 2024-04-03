package com.SCAI.ecommerce.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
// Controller di test per testare il root endpoint
@RestController
public class HomeController {
    @GetMapping
    public String homeController() {
        return "homecontroller funziona";
    }
}
