package com.projectpalm.econvaults.econvaults.Vault;

import net.milkbowl.vault.economy.Economy;
import net.milkbowl.vault.economy.EconomyResponse;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;

import java.util.List;
public class VaultImplementation implements Economy {
    @Override
    public boolean isEnabled() {
        return false;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public boolean hasBankSupport() {
        return false;
    }

    @Override
    public int fractionalDigits() {
        return 0;
    }

    @Override
    public String format(double amount) {
        return null;
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
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean hasAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean hasAccount(OfflinePlayer player, String worldName) {
        return false;
    }

    @Override
    public double getBalance(String playerName) {
        double rawData = data.ReadPlayerMoney(playerName);
        if (rawData >= 0){
            return rawData;
        }
        else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[EconVaults] Vault Implementation Error: " + ChatColor.BOLD +ChatColor.RED + rawData);
            Player player = Bukkit.getPlayer(playerName);
            player.sendMessage(ChatColor.RED + "An Error has occurred, please contact your server Admin");
            return -1;
        }
    }

    @Override
    public double getBalance(OfflinePlayer player) {
        String playerName = player.getName();
        int rawData = data.ReadPlayerMoney(playerName);
        if (rawData < 0){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[EconVaults] Vault Implementation Error: " + ChatColor.BOLD +ChatColor.RED + rawData);
        }
        return rawData;
    }

    @Override
    public double getBalance(String playerName, String world) {
        int rawData = data.ReadPlayerMoney(playerName);
        if (rawData >= 0){
            return rawData;
        }
        else{
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[EconVaults] Vault Implementation Error: " + ChatColor.BOLD +ChatColor.RED + rawData);
            Player player = Bukkit.getPlayer(playerName);
            player.sendMessage(ChatColor.RED + "An Error has occurred, please contact your server Admin");
            return -1;
        }
    }

    @Override
    public double getBalance(OfflinePlayer player, String world) {
        String playerName = player.getName();
        int rawData = data.ReadPlayerMoney(playerName);
        if (rawData < 0){
            Bukkit.getConsoleSender().sendMessage(ChatColor.RED + "[EconVaults] Vault Implementation Error: " + ChatColor.BOLD +ChatColor.RED + rawData);
        }
        return rawData;
    }

    @Override
    public boolean has(String playerName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, double amount) {
        return false;
    }

    @Override
    public boolean has(String playerName, String worldName, double amount) {
        return false;
    }

    @Override
    public boolean has(OfflinePlayer player, String worldName, double amount) {
        return false;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, double amount) {
        data.AdjustPlayerMoney(playerName, (int)amount*-1);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, double amount) {
        String playerName = player.getName();
        data.AdjustPlayerMoney(playerName, (int)amount*-1);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(String playerName, String worldName, double amount) {
        data.AdjustPlayerMoney(playerName, (int)amount*-1);
        return null;
    }

    @Override
    public EconomyResponse withdrawPlayer(OfflinePlayer player, String worldName, double amount) {
        String playerName = player.getName();
        data.AdjustPlayerMoney(playerName, (int)amount*-1);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, double amount) {
        data.AdjustPlayerMoney(playerName, (int)amount);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, double amount) {
        String playerName = player.getName();
        data.AdjustPlayerMoney(playerName, (int)amount);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(String playerName, String worldName, double amount) {
        data.AdjustPlayerMoney(playerName, (int)amount);
        return null;
    }

    @Override
    public EconomyResponse depositPlayer(OfflinePlayer player, String worldName, double amount) {
        String playerName = player.getName();
        data.AdjustPlayerMoney(playerName, (int)amount);
        return null;
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
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(String playerName, String worldName) {
        return false;
    }

    @Override
    public boolean createPlayerAccount(OfflinePlayer player, String worldName) {
        return false;
    }
}
