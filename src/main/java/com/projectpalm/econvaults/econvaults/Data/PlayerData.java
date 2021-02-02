package com.projectpalm.econvaults.econvaults.Data;

import org.bukkit.entity.Player;

import java.util.UUID;

public class PlayerData {
    private String Name; // Name of player
    private Integer Money; // how much money they have
    private UUID uuid; // player uuid (The same one that shows up when a player joins a server)

    public String GetName(){
        return Name;
    }
    public void SetName(String name){
        Name = name;
    }

    public Integer GetMoney(){
        return Money;
    }
    public void SetMoney(int money){
        Money = money;
    }

    public UUID GetUuid(){
        return uuid;
    }
    public void SetUuid(UUID uuid1){
        uuid = uuid1;
    }
}
