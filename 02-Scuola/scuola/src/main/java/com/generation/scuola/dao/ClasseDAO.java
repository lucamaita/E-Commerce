package com.generation.scuola.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.scuola.entities.Classe;
import com.generation.scuola.entities.Entity;

public class ClasseDAO implements IDAO{

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Override
    public void create(Entity e) {
        String query = "insert into classi(sezione) values (?)";
        PreparedStatement ps = null;
        try{
            Classe c = (Classe)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, c.getSezione());

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
        String query = "select * from classi";
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
                params.put("sezione", rs.getString(2));


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                Classe e = context.getBean(Classe.class, params);



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


    public Classe readFromId(int idClasse){
        String query = "select * from classi where id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Classe e = null;
        try{
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, idClasse);
            
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
                params.put("sezione", rs.getString(2));


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                e = context.getBean(Classe.class, params);
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
        return e;
    }


    public Map<Integer, Entity> readFromIdDocente(int idDocente){
        String query = "select c.* from classi_docenti cd inner join classi c on c.id = cd.id_classe where id_docente = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Map<Integer, Entity> ris = new HashMap<>();
        try{
            ps = database.getConnection().prepareStatement(query);
            ps.setInt(1, idDocente);
            
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
                params.put("sezione", rs.getString(2));


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
                Classe c = context.getBean(Classe.class, params);
                ris.put(c.getId(), c);
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
        String query = "update classe set sezione = ? where id = ?";
        PreparedStatement ps = null;
        try{
            Classe c = (Classe)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, c.getSezione());
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
        String query = "delete from classi where id = ?";
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
