package com.generation.scuola.entities;

import java.sql.Date;

public abstract class Persona extends Entity{
    private String nome;
    private String cognome;
    private Date dataNascita;
    private String username;
    private String password;


    public Persona(int id, String nome, String cognome, Date dataNascita, String username, String password) {
        super(id);
        this.nome = nome;
        this.cognome = cognome;
        this.dataNascita = dataNascita;
        this.username = username;
        this.password = password;
    }


    public String getNome() {
        return nome;
    }


    public void setNome(String nome) {
        this.nome = nome;
    }


    public String getCognome() {
        return cognome;
    }


    public void setCognome(String cognome) {
        this.cognome = cognome;
    }


    public Date getDataNascita() {
        return dataNascita;
    }


    public void setDataNascita(Date dataNascita) {
        this.dataNascita = dataNascita;
    }


    public String getUsername() {
        return username;
    }


    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }


    public void setPassword(String password) {
        this.password = password;
    }

    
    
}
