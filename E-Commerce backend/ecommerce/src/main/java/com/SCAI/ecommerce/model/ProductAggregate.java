package com.SCAI.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
@Getter
@Setter
public class ProductAggregate {
    private String nome;
    private String descrizione;
    private String foto;
    private double prezzo;
    private List<String> taglie;
    private List<String> colori;
    private String genere;
    private String accessorio;

    public ProductAggregate(Product product) {
        this.nome = product.getNome();
        this.descrizione = product.getDescrizione();
        this.foto = product.getFoto();
        this.prezzo = product.getPrezzo();
        this.taglie = new ArrayList<>();
        this.colori = new ArrayList<>();
        this.genere = product.getGenere();
        this.accessorio = product.getAccessorio(); // Converti booleano in stringa

        // Aggiungi taglia solo se non è già presente nella lista
        if (!this.taglie.contains(product.getTaglia())) {
            this.taglie.add(product.getTaglia());
        }

        // Aggiungi colore solo se non è già presente nella lista
        if (!this.colori.contains(product.getColore())) {
            this.colori.add(product.getColore());
        }
    }

    public void addTaglie(String taglia) {
        if (!this.taglie.contains(taglia)) {
            this.taglie.add(taglia);
        }
    }

    public void addColore(String colore) {
        if (!this.colori.contains(colore)) {
            this.colori.add(colore);
        }
    }

    // Getters and setters for other properties (nome, descrizione, foto, prezzo, genere, accessorio)
}
