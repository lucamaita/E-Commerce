package com.generation.bar.configurations;

import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import com.generation.bar.entities.Bevanda;
import com.generation.bar.entities.Snack;

@Configuration
public class EntitiesContext {
    

    //I @Bean con @Scope("prototype") sono sempre dei bean che finiscono nel context di spring, ma con "prototype" 
    //dico a spring di istanziarli ogni volta che li chiedo, pertanto non saranno singleton.
    //Per poterli istanziare devo chiedere al context di spring che richiamo tramite l'ApplicationContext che posso mettere in @Autowired dove ne ho bisogno
    @Bean
    @Scope("prototype")
    public Bevanda newBevanda(Map<String, String> params){
        /*
         * params:
         * 
         *  id      --> [ID]
         *  nome    --> [NOME]
         *  prezzo  --> [PREZZO]
         * 
         * 
         */



        Bevanda b = new Bevanda(
            Integer.parseInt(params.get("id")),
            params.get("nome"),
            Double.parseDouble(params.get("prezzo"))
        );
        return b;
    }


    // @Bean
    // @Scope("prototype")
    // public Bevanda newBevanda(int id, String nome, double prezzo){
    //     /*
    //      * params:
    //      * 
    //      *  id      --> [ID]
    //      *  nome    --> [NOME]
    //      *  prezzo  --> [PREZZO]
    //      * 
    //      * 
    //      */



    //     Bevanda b = new Bevanda(
    //         id,
    //         nome,
    //         prezzo
    //     );
    //     return b;
    // }
    
    @Bean
    @Scope("prototype")
    public Snack newSnack(Map<String, String> params){

        /*
         * params:
         * 
         *  id          --> [ID]
         *  nome        --> [NOME]
         *  prezzo      --> [PREZZO]
         *  quantita    --> [QUANTITA]
         *  salato      --> [salato]
         * 
         */
        Snack s = new Snack(
            Integer.parseInt(params.get("id")),
            params.get("nome"),
            Double.parseDouble(params.get("prezzo")),
            Integer.parseInt(params.get("quantita")), 
            Boolean.parseBoolean(params.get("salato"))
        );
        return s;
    }


}
