package com.generation.scuola.entities;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Docente extends Persona{
    List<Classe> listaClassi = new ArrayList<>();

    public Docente(int id, String nome, String cognome, Date dataNascita, String username, String password) {
        super(id, nome, cognome, dataNascita, username, password);
    }

    public List<Classe> getListaClassi() {
        return listaClassi;
    }

    public void setListaClassi(List<Classe> listaClassi) {
        this.listaClassi = listaClassi;
    }

    
}
