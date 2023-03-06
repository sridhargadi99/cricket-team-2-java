/*
 * You can use the following import statements
 * import org.springframework.beans.factory.annotation.Autowired;
 * import org.springframework.http.HttpStatus;
 * import org.springframework.jdbc.core.JdbcTemplate;
 * import org.springframework.stereotype.Service;
 * import org.springframework.web.server.ResponseStatusException;
 * import java.util.ArrayList;
 * 
 */

// Write your code here
package com.example.player.service;

import com.example.player.model.Player;
import com.example.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.*;
import com.example.player.model.PlayerRowMapper;

@Service
public class PlayerH2Service implements PlayerRepository{
    @Autowired
    private JdbcTemplate db;
    @Override
    public ArrayList<Player> getPlayerDetails(){
        List<Player> playerList = db.query("SELECT * FROM TEAM" , new PlayerRowMapper());
        ArrayList<Player> players = new ArrayList<>(playerList);
        return players;

    }

    @Override
    public Player addPlayer(Player player){
        db.update("INSERT INTO TEAM(playerName,jerseyNumber,role) VALUES(?,?,?)", player.getPlayerName(), player.getJerseyNumber(), player.getRole());
        Player playerDetails = db.queryForObject("SELECT * FROM TEAM WHERE playerName = ? AND jerseyNumber = ? AND role = ?", new PlayerRowMapper(), player.getPlayerName(), player.getJerseyNumber(), player.getRole());
        return playerDetails;
    }

    @Override
    public Player getPlayer(int playerId){
        try{
            Player playerDetails = db.queryForObject("SELECT * FROM TEAM WHERE playerId = ?", new PlayerRowMapper(), playerId);
            return playerDetails;
        }catch(Exception e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

    }

    @Override
    public Player updatePlayer(int playerId, Player player){
        if(player.getPlayerName() != null){
            db.update("UPDATE TEAM SET playerName = ? WHERE playerId = ?", player.getPlayerName(), playerId);
        }
        if(player.getJerseyNumber() != 0){
            db.update("UPDATE TEAM SET jerseyNumber = ? WHERE playerID = ?", player.getJerseyNumber(),playerId);
        }
        if(player.getRole() != null){
            db.update("UPDATE TEAM SET role = ? WHERE playerId = ? ", player.getRole(),playerId);
        }
        return getPlayer(playerId);
    } 
    
    @Override
    public void DeletePlayer(int playerId){
        db.update("DELETE FROM TEAM WHERE playerId = ?", playerId);
    }

}
