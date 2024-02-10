package com.maita.GameStop.entities;

public class GamePlatform {

    //ATTRIBUTES
    private int game_id;
    private int platform_id;

    //CONSTRUCTOR
    public GamePlatform(int game_id, int platform_id) {
        this.game_id = game_id;
        this.platform_id = platform_id;
    }
    
    //GETTERS & SETTERS
    public int getGame_id() {
        return game_id;
    }

    public void setGame_id(int game_id) {
        this.game_id = game_id;
    }

    public int getPlatform_id() {
        return platform_id;
    }

    public void setPlatform_id(int platform_id) {
        this.platform_id = platform_id;
    }
    
    
    
}
