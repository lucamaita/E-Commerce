package com.maita.prova.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String nome;
    private String foto;
    private String codice_articolo;
    private String categoria;
    private Double prezzo;
    private String colore;
    private String taglia;
    private Double peso;
    private Integer quantita;
    private LocalDate dataInserimento;
}
