package com.generation.scuola.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.generation.scuola.entities.Classe;
import com.generation.scuola.entities.Docente;
import com.generation.scuola.entities.Entity;
import com.generation.scuola.entities.Studente;

public class DocenteDAO implements IDAO{
    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;

    @Autowired
    private ClasseDAO classeDAO;

    @Override
    public void create(Entity e) {
        String query = "insert into persone(nome, cognome, data_nascita) values(?, ?, ?)";
        PreparedStatement ps = null;
        try{
            Studente s = (Studente)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setString(1, s.getCognome());
            ps.setDate(3, s.getDataNascita());

            ps.executeUpdate();

            //Per inserire la chiave esterna nella tabella studenti devo recuperare l'id appena inserito dalla tabella persone
            //pertanto faccio una subquery in fase di insert alla tabella persone recuperando il massimo id persente (che sarà sicuramente l'ultimo inserito)
            //per aggiungerlo come valore della chiave esterna dello studente che sto inserendo
            query = "insert into docenti(id) values((select max(id) from persone))";
            ps = database.getConnection().prepareStatement(query);

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
        String query = "select * from persone p inner join docenti d on p.id = d.id";
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
                params.put("cognome", rs.getString(3));
                params.put("dataNascita", rs.getDate(4).toString());
                params.put("username", rs.getString(5));
                params.put("password", rs.getString(6));
                //params.put("idClasse", rs.getInt(8)+"");

                Docente e = context.getBean(Docente.class, params);
                List<Classe> listaClassi = new ArrayList<>();
                Map<Integer, Entity> result = classeDAO.readFromIdDocente(e.getId());

                for(Entity classe : result.values()){
                    listaClassi.add((Classe)classe);
                }

                e.setListaClassi(listaClassi);

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

    public Docente readFromId(int idDocente){
        String query = "select * from persone p inner join docenti d on p.id = d.id where d.id = ?";
        PreparedStatement ps = null;
        ResultSet rs = null;
        Docente e = null;
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
                params.put("nome", rs.getString(2));
                params.put("cognome", rs.getString(3));
                params.put("dataNascita", rs.getDate(4).toString());
                params.put("username", rs.getString(5));
                params.put("password", rs.getString(6));
                //params.put("idClasse", rs.getInt(8)+"");

                e = context.getBean(Docente.class, params);
                List<Classe> listaClassi = new ArrayList<>();
                Map<Integer, Entity> result = classeDAO.readFromIdDocente(e.getId());

                for(Entity classe : result.values()){
                    listaClassi.add((Classe)classe);
                }

                e.setListaClassi(listaClassi);


                // context.getBean(Snack.class, rs.getInt(1), rs.getString(2), rs.getDouble(3));
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


    @Override
    public void update(Entity e) {
        String query = "update persone set nome=?, cognome=?, data_nascita=? where id=?";
        PreparedStatement ps = null;
        try{
            Studente s = (Studente)e;
            ps = database.getConnection().prepareStatement(query);
            ps.setString(1, s.getNome());
            ps.setString(2, s.getCognome());
            ps.setDate(3, s.getDataNascita());  
            ps.setInt(4, s.getId());

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
        String query = "delete from persone where id = ?";
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
