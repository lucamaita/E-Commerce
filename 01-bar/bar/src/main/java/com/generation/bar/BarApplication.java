package com.generation.bar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.generation.bar.entities.Snack;

@SpringBootApplication
public class BarApplication {
	/*
	Scaletta generale consigliata da seguire:
		- Creare il DB
		- Creare i modelli(entities)
		- Creare i DAO con i metodi CRUD di base inizialmente
		- Impostare il context di spring, creare tutte le classi di configurazione (@Configuration) e i @Bean necesari
		- Creare i Service
		- Creare i Controller
		- Creare le pagine html
	 */
	public static void main(String[] args) {
		SpringApplication.run(BarApplication.class, args);
	}

}
