package com.maita.GameStop.entities;

import java.time.LocalDate;

public class Game {

    //ATTRIBUTES
    private int id;
    private String title;
    private String genre;
    private Double rating;
    private LocalDate release_date;
    
    //CONSTRUCTOR
    public Game(int id, String title, String genre, Double rating, LocalDate release_date) {
        this.id = id;
        this.title = title;
        this.genre = genre;
        this.rating = rating;
        this.release_date = release_date;
    }
    
    //GETTERS & SETTERS   
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }
    public String getGenre() {
        return genre;
    }
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public Double getRating() {
        return rating;
    }
    public void setRating(Double rating) {
        this.rating = rating;
    }
    public LocalDate getRelease_date() {
        return release_date;
    }
    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    //METHODS

    
    
}
