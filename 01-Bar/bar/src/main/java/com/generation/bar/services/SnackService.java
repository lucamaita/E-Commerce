package com.generation.bar.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;

import com.generation.bar.dao.SnackDAO;
import com.generation.bar.entities.Entity;
import com.generation.bar.entities.Snack;

public class SnackService {
    
    @Autowired
    private SnackDAO snackDao;

    public List<Snack> findAll(){
        Map<Integer, Entity> data = snackDao.read();
        List<Snack> ris = new ArrayList<>();


        for(Entity e : data.values()){
            if(e instanceof Snack){
                ris.add((Snack)e);
            }
        }

        return ris;
    }

    public Snack findById(int id){
        Map<Integer, Entity> data = snackDao.read();

        return (Snack)data.get(id);
    }

    public List<Snack> findByNome(String nome){
        Map<Integer, Entity> data = snackDao.read(nome);
        List<Snack> ris = new ArrayList<>();

        for(Entity e : data.values()){
            if(e instanceof Snack){
                ris.add((Snack)e);
            }
        }

        return ris;
    }

}
