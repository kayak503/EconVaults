package com.projectpalm.econvaults.econvaults.commands;

import com.jayway.jsonpath.JsonPath;
import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.Errors;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import java.util.List;
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
                sender.sendMessage(ChatColor.GOLD + "<t> MyVaults - shows you all the vaults you own");
                sender.sendMessage(ChatColor.GOLD + "<t> AddVault");
                sender.sendMessage(ChatColor.GOLD + "<t> EditVaults");
                sender.sendMessage(ChatColor.GOLD + "<t> RemoveVault");
            } else if (args[0].equalsIgnoreCase("MyVaults")) {
                VaultData Vaults[] = Data.GetAllPlayerVaults(player);
                if (Vaults != null) {
                    String vaultNames = "";
                    for (int i = 0; i < Vaults.length; i++) {
                        VaultData vault = Vaults[i];
                        vaultNames += " " + vault.GetName();
                        if (i != Vaults.length - 1) {
                            vaultNames += ",";
                        }
                    }
                    sender.sendMessage(vaultNames);
                } else {
                    sender.sendMessage("No Vaults have been set up yet");
                }

            }

        }
        return false;
    }
}
