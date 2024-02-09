package com.generation.bar.dao;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import com.generation.bar.entities.Entity;
import com.generation.bar.entities.Snack;

public class SnackDAO implements IDAO{
    
    //Autowired chiede a spring di trovare un bean all'interno del suo context da iniettare su questa proprieta'
    //(il Bean da cercare deve essere dello stesso tipo della proprieta' contrassegnata, e deve ovviamente essere definito all'interno di una classe di configurazione)
    @Autowired
    private Database database;

    //L'ApplicationContext rappresenta il context di spring (dove vanno a finire tutti i @Bean) ed esso stesso è un @Bean
    //che spring è in grado di autoniettare, posso usarlo per accedere poi manualmente al context di spring e richiamarmi i @Bean che mi servono (solitamente i "prototype")
    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into snacks(nome, prezzo, quantita, salato) values (?, ?, ?, ?)";
        PreparedStatement ps = null;
        try{
            Snack s = (Snack)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getPrezzo());
            ps.setInt(3, s.getQuantita());
            ps.setBoolean(4, s.isSalato());

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore inserimento Snack: " + exc.getMessage());
        }
        catch(ClassCastException exc){
            System.out.println("Errore tipo dato errato in SnackDAO");
        }
        finally{
            try{
                ps.close();
            }
            catch(Exception exc){
                System.out.println("Errore chiusura PreparedStatement");
            }
        }
    }

    @Override
    public Map<Integer, Entity> read() {
        String query = "select * from snacks";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Entity> ris = new HashMap<>();


        try{
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while(rs.next()){
                // Entity e = new Snack(
                //     rs.getInt(1),       //ID 
                //     rs.getString(2),    //NOME
                //     rs.getDouble(3),    //PREZZO
                //     rs.getInt(4),       //QUANTITA
                //     rs.getBoolean(5));  //SALATO
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("nome", rs.getString(2));
                params.put("prezzo", rs.getDouble(3)+"");
                params.put("quantita", rs.getInt(4)+"");
                params.put("salato", rs.getBoolean(5)+"");


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                Snack e = context.getBean(Snack.class, params);



                ris.put(e.getId(), e);
            }
        }
        catch(SQLException exc){
            System.out.println("Errore nella select in SnackDAO");
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception exc){
                System.out.println("Errore chiusura PreparedStatement");
            }
        }
        
        return ris;
    }


    public Map<Integer, Entity> read(String nome){
        String query = "select * from snacks where nome like '%?%'";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Entity> ris = new HashMap<>();


        try{
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, nome);
            rs = ps.executeQuery();

            while(rs.next()){
                // Entity e = new Snack(
                //     rs.getInt(1),       //ID 
                //     rs.getString(2),    //NOME
                //     rs.getDouble(3),    //PREZZO
                //     rs.getInt(4),       //QUANTITA
                //     rs.getBoolean(5));  //SALATO
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("nome", rs.getString(2));
                params.put("prezzo", rs.getDouble(3)+"");
                params.put("quantita", rs.getInt(4)+"");
                params.put("salato", rs.getBoolean(5)+"");

                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                Snack e = context.getBean(Snack.class, params);
                ris.put(e.getId(), e);
            }
        }
        catch(SQLException exc){
            System.out.println("Errore nella select in SnackDAO");
        }
        finally{
            try{
                ps.close();
                rs.close();
            }
            catch(Exception exc){
                System.out.println("Errore chiusura PreparedStatement");
            }
        }
        
        return ris;
    }

    @Override
    public void update(Entity e) {
        String query = "update snacks set nome = ?, prezzo = ?, quantita = ?, salato = ? where id = ?";
        PreparedStatement ps = null;
        try{
            Snack s = (Snack)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getPrezzo());
            ps.setInt(3, s.getQuantita());
            ps.setBoolean(4, s.isSalato());
            ps.setInt(5, s.getId());

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore aggiornamento Snack: " + exc.getMessage());
        }
        catch(ClassCastException exc){
            System.out.println("Errore tipo dato errato in SnackDAO");
        }
        finally{
            try{
                ps.close();
            }
            catch(Exception exc){
                System.out.println("Errore chiusura PreparedStatement");
            }
        }
    }

    @Override
    public void delete(int id) {
        String query = "delete from snacks where id = ?";
        PreparedStatement ps = null;
        try{
            ps = database.getConnection().prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore eliminazione Snack: " + exc.getMessage());
        }
        finally{
            try{
                ps.close();
            }
            catch(Exception exc){
                System.out.println("Errore chiusura PreparedStatement");
            }
        }

    }

    
}
