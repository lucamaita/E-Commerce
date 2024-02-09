package com.generation.bar.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.bar.entities.Bevanda;
import com.generation.bar.entities.Entity;
import com.generation.bar.entities.Snack;

public class BevandaDAO implements IDAO{

    //Autowired chiede a spring di trovare un bean all'interno del suo context da iniettare su questa proprieta'
    //(il Bean da cercare deve essere dello stesso tipo della proprieta' contrassegnata, e deve ovviamente essere definito all'interno di una classe di configurazione)
    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into bevande(nome, prezzo) values (?, ?)";
        PreparedStatement ps = null;
        try{
            Bevanda s = (Bevanda)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getPrezzo());

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore inserimento Bevanda: " + exc.getMessage());
        }
        catch(ClassCastException exc){
            System.out.println("Errore tipo dato errato in BevandaDAO");
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
        //Mi scrivo la query da eseguire
        String query = "select * from bevande";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Entity> ris = new HashMap<>();


        try{
            //Chiedo alla connessione di preparare la query tramite PreparedStatement
            ps = database.getConnection().prepareStatement(query);

            //Eseguo la query sul db (equivale al tasto con il fulmine su MySQL)
            rs = ps.executeQuery();

            //Mi ciclo il risultato del db
            //ResultSet rappresenta la tabella e la lettura funziona similarmente al file
            while(rs.next()){
                // Entity e = new Bevanda(
                //     rs.getInt(1),       //ID 
                //     rs.getString(2),    //NOME
                //     rs.getDouble(3)    //PREZZO
                //     );  

                //Mi preparo una mappa con i parametri dell'oggetto Bevanda che voglio istanziare
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("nome", rs.getString(2));
                params.put("prezzo", rs.getDouble(3)+"");


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                
                //Chiedo al context di spring (che ho recuperato grazie all'Autowired fatto sopra) di istanziarmi un Bean di tipo Bevanada con la mappa parametri che ho preparato
                Bevanda e = context.getBean(Bevanda.class, params);

                //Inserisco l'oggetto appena creato nella mappa degli oggetti associato al suo id come chiave
                ris.put(e.getId(), e);
            }
        }
        catch(SQLException exc){
            System.out.println("Errore nella select in BevandaDAO");
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

    public Map<Integer, Entity> read(String nome) {
        //Mi scrivo la query da eseguire
        String query = "select * from bevande where nome like '%?%'";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Entity> ris = new HashMap<>();


        try{
            //Chiedo alla connessione di preparare la query tramite PreparedStatement
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, nome);
            //Eseguo la query sul db (equivale al tasto con il fulmine su MySQL)
            rs = ps.executeQuery();

            //Mi ciclo il risultato del db
            //ResultSet rappresenta la tabella e la lettura funziona similarmente al file
            while(rs.next()){
                // Entity e = new Bevanda(
                //     rs.getInt(1),       //ID 
                //     rs.getString(2),    //NOME
                //     rs.getDouble(3)    //PREZZO
                //     );  

                //Mi preparo una mappa con i parametri dell'oggetto Bevanda che voglio istanziare
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1)+"");
                params.put("nome", rs.getString(2));
                params.put("prezzo", rs.getDouble(3)+"");


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                
                //Chiedo al context di spring (che ho recuperato grazie all'Autowired fatto sopra) di istanziarmi un Bean di tipo Bevanada con la mappa parametri che ho preparato
                Bevanda e = context.getBean(Bevanda.class, params);

                //Inserisco l'oggetto appena creato nella mappa degli oggetti associato al suo id come chiave
                ris.put(e.getId(), e);
            }
        }
        catch(SQLException exc){
            System.out.println("Errore nella select in BevandaDAO");
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
        String query = "update bevande set nome = ?, prezzo = ? where id = ?";
        PreparedStatement ps = null;
        try{
            Bevanda s = (Bevanda)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setDouble(2, s.getPrezzo());
            ps.setInt(3, s.getId());

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore aggiornamento Bevanda: " + exc.getMessage());
        }
        catch(ClassCastException exc){
            System.out.println("Errore tipo dato errato in BevandaDAO");
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
        String query = "delete from bevande where id = ?";
        PreparedStatement ps = null;
        try{
            ps = database.getConnection().prepareStatement(query);

            ps.setInt(1, id);

            ps.executeUpdate();
        }
        catch(SQLException exc){
            System.out.println("Errore eliminazione Bevanda: " + exc.getMessage());
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
