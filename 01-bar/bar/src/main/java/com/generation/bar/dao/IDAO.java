package com.generation.bar.dao;

import java.util.Map;

import com.generation.bar.entities.Entity;

public interface IDAO {
    
    public void create(Entity e);

    public Map<Integer, Entity> read();

    public void update(Entity e);

    public void delete(int id);

}
