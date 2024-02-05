package com.generation.bar.entities;

public class Snack extends Entity{
    private int quantita;
    private boolean salato;


    public Snack(int id, String nome, double prezzo, int quantita, boolean salato) {
        super(id, nome, prezzo);
        this.quantita = quantita;
        this.salato = salato;
    }


    public int getQuantita() {
        return quantita;
    }


    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }


    public boolean isSalato() {
        return salato;
    }


    public void setSalato(boolean salato) {
        this.salato = salato;
    }

    
}
