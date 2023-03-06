/*
 * 
 * You can use the following import statemets
 * 
 * import org.springframework.web.bind.annotation.*;
 * import java.util.*;
 * 
 */
package com.example.player.controller;

import org.springframework.web.bind.annotation.*;
import java.util.*;
import com.example.player.model.Player;
import com.example.player.service.PlayerH2Service;
import org.springframework.beans.factory.annotation.Autowired;

@RestController 
public class PlayerController{

    @Autowired 
    public PlayerH2Service playerService;
    
    @GetMapping("/players")
    public ArrayList<Player> getPlayerDetails(){
        return playerService.getPlayerDetails();
    }

    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
        return playerService.addPlayer(player);
    }
    
    @GetMapping("/players/{playerId}")
    public Player getPlayer(@PathVariable("playerId") int playerId){
        return playerService.getPlayer(playerId);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player){
        return playerService.updatePlayer(playerId, player);
    }

    @DeleteMapping("/players/{playerId}")
    public void DeletePlayer(@PathVariable("playerId") int playerId){
        playerService.DeletePlayer(playerId);
    }

    
}