package com.projectpalm.econvaults.econvaults.Data;

import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;

import java.util.Map;
import java.util.UUID;

public class VaultData {
    private String Name;
    private Integer[][] VaultPosition;
    private Integer[] TellerPosition;
    private Map<Material,Integer> Contents; // Item material, The amount they have
    private String[] Owners;
    private String Team;
    private UUID uuid;
    private World World;

    public void SetName(String name){
        Name = name;
    }
    public String GetName(){
        return Name;
    }
    public void SetVaultPosition(Integer[][] xxs){
        VaultPosition = xxs;
    }
    public Integer[][] GetVaultPosition(){
        return VaultPosition;
    }
    public void SetTellerPosition (Integer[] xs ){
        TellerPosition = xs;
    }
    public Integer[] GetTellerPosition(){
        return TellerPosition;
    }
    public void SetOwners(String[] strings){
        Owners = strings;
    }
    public String[] GetOwners(){
        return Owners;
    }
    public void SetTeam(String team){
        Team = team;
    }
    public String GetTeam(){
        return Team;
    }
    public UUID GetUuid() {
        return uuid;
    }
    public void GenUuid(){
        if (uuid == null){
           uuid = UUID.randomUUID();
        }
    }
    public void SetContentsMap(Map<Material,Integer> map){
        Contents = map;
    }
    public Map<Material,Integer> GetContentsMap(){
        return Contents;
    }



    public int GetTellerX(){
        return TellerPosition[0];
    }
    public int GetTellerY(){
        return TellerPosition[1];
    }
    public int GetTellerZ(){
        return TellerPosition[2];
    }


}
