package com.generation.scuola.entities;

public class Classe extends Entity{
    private String sezione;

    public Classe(int id, String sezione) {
        super(id);
        this.sezione = sezione;
    }

    public String getSezione() {
        return sezione;
    }

    public void setSezione(String sezione) {
        this.sezione = sezione;
    }

    
}
