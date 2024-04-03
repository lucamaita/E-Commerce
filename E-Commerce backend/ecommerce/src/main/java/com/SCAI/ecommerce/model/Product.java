package com.SCAI.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
// Questo blocco di codice definisce l'entità Product,
// utilizzando le annotazioni di JPA per mappare la classe a una tabella del database
// e le annotazioni di Lombok per generare automaticamente i metodi getter e setter.
// Include campi per identificare un prodotto, come ID, nome, descrizione, foto, codice articolo, categoria, prezzo,
// colore, taglia, genere, accessorio, peso, quantità disponibile e data di inserimento del prodotto nel sistema.
@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String descrizione;
    private String foto;
    private String codice_articolo;
    private String categoria;
    private Double prezzo;
    private String colore;
    private String taglia;
    private String genere;
    private String accessorio;
    private Double peso;
    private Integer quantita;
    private LocalDate dataInserimento;
}
