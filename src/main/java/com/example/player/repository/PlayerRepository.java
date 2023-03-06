// Write your code here
package com.example.player.repository;

import java.util.ArrayList;
import com.example.player.model.Player;

public interface PlayerRepository{
    ArrayList<Player> getPlayerDetails();
    Player addPlayer(Player player);
    Player getPlayer(int playerId);
    Player updatePlayer(int playerId, Player player);
    void DeletePlayer(int playerI);
    
}