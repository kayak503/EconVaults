package com.projectpalm.econvaults.econvaults.Vault;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.PlayerData;
import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class VaultImplementation implements Economy {
    @Override
    public boolean isEnabled() {
        return true;
    }

    @Override
    public String getName() {
        return "EconVaults";
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0; // no digits data is stored as an int
    }

    @Override
    public String format(double amount) {
        if((int) amount == 1){return currencyNameSingular(); }
        return currencyNamePlural();
    }

    @Override
    public String currencyNamePlural() {
        return "coins";
    }

    @Override
    public String currencyNameSingular() {
        return "coin";
    }

    @Override
    public boolean hasAccount(String playerName) {
        PlayerData playerData = Data.GetPlayerData(playerName);
        return playerData != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        return playerData != null;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName){
        PlayerData playerData = Data.GetPlayerData(playerName);
        return playerData != null;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName)  {
        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        return playerData != null;
    }

    @Override
    public double getBalance(String playerName) {
        if (!hasAccount(playerName)){ createPlayerAccount(playerName); return 0;}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            return playerData.GetMoney();
        }
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        if (!hasAccount(player)){ createPlayerAccount(player); return 0;}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            return playerData.GetMoney();
        }
        return 0;
    }

    @Override
    public double getBalance(String playerName, String world) {
        if (!hasAccount(playerName)){ createPlayerAccount(playerName); return 0;}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            return playerData.GetMoney();
        }
        return 0;
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        if (!hasAccount(player)){ createPlayerAccount(player); return 0;}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            return playerData.GetMoney();
        }
        return 0;
    }

    @Override
    public boolean has(String playerName, double amount) {
        if (!hasAccount(playerName)){ createPlayerAccount(playerName); }

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            return playerData.GetMoney() >= (int) amount;
        }
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        if (!hasAccount(player)){ createPlayerAccount(player); }

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            return playerData.GetMoney() >= (int) amount;
        }
        return false;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        if (!hasAccount(playerName)){ createPlayerAccount(playerName); }

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            return playerData.GetMoney() >= (int) amount;
        }
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)){ createPlayerAccount(player); }

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            return playerData.GetMoney() >= (int) amount;
        }
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        if (!hasAccount(playerName)){createPlayerAccount(playerName);}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            int newBalance = playerData.GetMoney() - ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney -= (int) amount;
            Data.UpdateNetMoney(NetMoney);


            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );

    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        if (!hasAccount(player)){createPlayerAccount(player);}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            int newBalance = playerData.GetMoney() - ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney -=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        if (!hasAccount(playerName)){createPlayerAccount(playerName);}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            int newBalance = playerData.GetMoney() - ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney -=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)){createPlayerAccount(player);}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            int newBalance = playerData.GetMoney() - ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney -=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        if (!hasAccount(playerName)){createPlayerAccount(playerName);}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            int newBalance = playerData.GetMoney() + ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney +=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        if (!hasAccount(player)){createPlayerAccount(player);}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            int newBalance = playerData.GetMoney() + ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney +=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        if (!hasAccount(playerName)){createPlayerAccount(playerName);}

        PlayerData playerData = Data.GetPlayerData(playerName);
        if(playerData != null){
            int newBalance = playerData.GetMoney() + ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney +=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        if (!hasAccount(player)){createPlayerAccount(player);}

        UUID playerUuid = player.getUniqueId();
        PlayerData playerData = Data.GetPlayerData(playerUuid);
        if(playerData != null){
            int newBalance = playerData.GetMoney() + ((int) amount);
            playerData.SetMoney(newBalance);
            Data.UpdatePlayerData(playerData);

            int NetMoney = Data.GetNetMoney();
            NetMoney +=  (int) amount;
            Data.UpdateNetMoney(NetMoney);
            return new EconomyResponse( amount, newBalance, EconomyResponse.ResponseType.SUCCESS,"no error" );
        }
        return new EconomyResponse( amount,0, EconomyResponse.ResponseType.FAILURE,"Player Data not found " );
    }

    @Override
    public EconomyResponse createBank(String name, String player) {
        return null;
    }

    @Override
    public EconomyResponse createBank(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse deleteBank(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankBalance(String name) {
        return null;
    }

    @Override
    public EconomyResponse bankHas(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankWithdraw(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse bankDeposit(String name, double amount) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankOwner(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, String playerName) {
        return null;
    }

    @Override
    public EconomyResponse isBankMember(String name, OfflinePlayer player) {
        return null;
    }

    @Override
    public List<String> getBanks() {
        return null;
    }

    @Override
    public boolean createPlayerAccount(String playerName) {
        if (hasAccount(playerName)){ return true; }

        Player player = Bukkit.getPlayer(playerName);
        if (player != null){
            PlayerData playerData = new PlayerData();
            playerData.SetMoney(0);
            playerData.SetUuid(player.getUniqueId());
            playerData.SetName(player.getName());
            Data.AddNewPlayerData(playerData);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        if (hasAccount(player)){ return true; }

        PlayerData playerData = new PlayerData();
        playerData.SetMoney(0);
        playerData.SetUuid(player.getUniqueId());
        playerData.SetName(player.getName());

        Data.AddNewPlayerData(playerData);
        return true;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        if (hasAccount(playerName)){ return true; }

        Player player = Bukkit.getPlayer(playerName);
        if (player != null){
            PlayerData playerData = new PlayerData();
            playerData.SetMoney(0);
            playerData.SetUuid(player.getUniqueId());
            playerData.SetName(player.getName());
            Data.AddNewPlayerData(playerData);
            return true;
        }
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        if (hasAccount(player)){ return true; }

        PlayerData playerData = new PlayerData();
        playerData.SetMoney(0);
        playerData.SetUuid(player.getUniqueId());
        playerData.SetName(player.getName());

        Data.AddNewPlayerData(playerData);
        return true;
    }
}
