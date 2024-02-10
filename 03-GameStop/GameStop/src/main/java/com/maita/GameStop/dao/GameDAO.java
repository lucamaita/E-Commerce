package com.maita.GameStop.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.catalina.core.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;

import com.maita.GameStop.db.Database;
import com.maita.GameStop.entities.Game;

public class GameDAO {

    @Autowired
    private Database database;

    @Autowired
    private ApplicationContext context;
    
    private Connection connection;
    
    public GameDAO(Connection connection) {
        this.connection = connection;
    }

    public Map<Integer, Game> read() {
        String query = "select * from games";
        PreparedStatement ps = null;
        ResultSet rs = null;

        Map<Integer, Game> ris = null;

        try{
            ps = database.getConnection().prepareStatement(query);
            rs = ps.executeQuery();

            while (rs.next()) {
                //public Game(int id, String title, String genre, Double rating, LocalDate release_date)
                Map<String, String> params = new HashMap<>();
                params.put("id", rs.getInt(1) + "");
                params.put("title", rs.getString(2));
                params.put("genre", rs.getString(3));
                params.put("rating", rs.getDouble(4) + "");
                params.put("release_date", rs.getDate(5).toString());

                Game game = context.getBean(Game.class, params);
            }
        }

        return null;
    }
}
