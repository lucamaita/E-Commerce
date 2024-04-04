package com.SCAI.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
@Setter
public class ProdottiAggregati {
    private String nome;
    private String descrizione;
    private String foto;
    private double prezzo;
    private List<String> taglie;
    private List<String> colori;
    private String genere;
    private String accessorio;

    // Abbiamo convertito i colori in esadecimale per facilitare
    // la graficazione lato frontend
    private static final Map<String, String> coloriEsadecimali = new HashMap<>();

    static {
        coloriEsadecimali.put("Nero", "#000000");
        coloriEsadecimali.put("Grigio", "#808080");
        coloriEsadecimali.put("Blu", "#0000FF");
        coloriEsadecimali.put("Rosso", "#FF0000");
        coloriEsadecimali.put("Bianco", "#FFFFFF");
        coloriEsadecimali.put("Verde", "#008000");
        coloriEsadecimali.put("Viola", "#800080");
        coloriEsadecimali.put("Rosa", "#FFC0CB");
        coloriEsadecimali.put("Lilla", "#C8A2C8");
        coloriEsadecimali.put("Arancione", "#FFA500");
        coloriEsadecimali.put("Giallo", "#FFFF00");
    }

    public ProdottiAggregati(Product product) {
        this.nome = product.getNome();
        this.descrizione = product.getDescrizione();
        this.foto = product.getFoto();
        this.prezzo = product.getPrezzo();
        this.taglie = new ArrayList<>();
        this.colori = new ArrayList<>();
        this.genere = product.getGenere();
        this.accessorio = product.getAccessorio();

        if (product.getTaglia() != null && !product.getTaglia().isEmpty()) {
            if (!this.taglie.contains(product.getTaglia())) {
                this.taglie.add(product.getTaglia());
            }
        }

        if (!this.colori.contains(product.getColore())) {
            String coloreEsadecimale = getColoreEsadecimale(product.getColore());
            this.colori.add(coloreEsadecimale);
        }
    }

    public void addTaglie(String taglia) {
        if (!this.taglie.contains(taglia)) {
            this.taglie.add(taglia);
        }
    }

    public void addColore(String colore) {
        String coloreEsadecimale = getColoreEsadecimale(colore);
        if (!this.colori.contains(coloreEsadecimale)) {
            this.colori.add(coloreEsadecimale);
        }
    }

    // Metodo per ottenere il valore esadecimale del colore
    public String getColoreEsadecimale(String colore) {
        return coloriEsadecimali.getOrDefault(colore, "Non specificato");
    }
}
