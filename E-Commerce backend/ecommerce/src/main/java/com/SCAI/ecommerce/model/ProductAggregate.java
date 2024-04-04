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

    public ProductAggregate(Product product) {
        this.nome = product.getNome();
        this.descrizione = product.getDescrizione();
        this.foto = product.getFoto();
        this.prezzo = product.getPrezzo();
        this.taglie = new ArrayList<>();
        this.colori = new ArrayList<>();
        this.taglie.add(product.getTaglia());
        this.colori.add(product.getColore());
    }

    public void addVariant(String taglia, String colore) {
        this.taglie.add(taglia);
        this.colori.add(colore);
    }

}
