package com.projectpalm.econvaults.econvaults.commands;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.GUI.VaultGuiMethods;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.regex.Pattern;

public class EconVaults implements CommandExecutor {
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (sender instanceof Player) {
            Player player = (Player) sender;

            if (args.length == 0 || args[0].equals("?")) {
                sender.sendMessage(ChatColor.GOLD + "Welcome to Econ Vaults! bellow are the basic commands to get you going");
                sender.sendMessage(ChatColor.GOLD + "<t> Vaults ~ Shows you All your current Vaults");
                sender.sendMessage(ChatColor.GOLD + "<t> Create ~ Create at Vault");
                sender.sendMessage(ChatColor.GOLD + "<t> Update ~ Update a Vault");
                sender.sendMessage(ChatColor.GOLD + "<t> RemoveVault [vault] ~ deletes the named vault");
            }

            else if (args[0].equalsIgnoreCase("Create")) {
                VaultGuiMethods.AddNewVault(player);
            }
            else if (args[0].equalsIgnoreCase("Vaults")) {
                VaultGuiMethods.ShowVaults(player);
            }
            else if (args[0].equalsIgnoreCase("Update")) {
                VaultGuiMethods.EditVault(player);
            }
            else if (args[0].equalsIgnoreCase("RemoveVault")) {
                if (args[1].isEmpty()){
                    player.sendMessage(ChatColor.RED + "Command Error, RemoveVault requires A valid vault name");
                    return false;
                }
                VaultData vault = Data.GetVaultByName(args[1], player);
                if(vault == null){
                    player.sendMessage(ChatColor.RED + "Vault Name Error, " + args[1] + " vault was not found");
                    return false;
                }
                Data.RemoveVault(vault);
                if (Data.GetVaultByName(args[1], player) == null){
                    player.sendMessage(ChatColor.GREEN + "Vault " + args[1] + " removed");
                }
                else{
                    player.sendMessage(ChatColor.RED + "Something went wrong :(");
                }

            }

            else if (args[0].equalsIgnoreCase("test")) {
                VaultData vault = Data.GetVaultByName("test",player);
                if (vault != null){
                    // Mob_Teller.createVaultTeller(player, vault);
                    com.projectpalm.econvaults.econvaults.EconVaults.getInstance.tab.spawnTeller(vault);
                    player.sendMessage("Happened");
                }
                else {
                    player.sendMessage("Fail");
                }
            }
            else if (args[0].equalsIgnoreCase("testKill")) {
                VaultData vault = Data.GetVaultByName("test", player);
                if (vault != null) {
                    // Mob_Teller.createVaultTeller(player, vault);
                    com.projectpalm.econvaults.econvaults.EconVaults.getInstance.tab.killTeller(vault.GetUuid());
                    player.sendMessage("Happened");
                } else {
                    player.sendMessage("Fail");
                }
            }

        }
        return false;
    }
}
