package com.projectpalm.econvaults.econvaults.commands;

import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.GUI.VaultExchange;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class money  implements CommandExecutor {
    private EconVaults plugin = EconVaults.getInstance;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equals("?")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                sender.sendMessage(plugin.economyImplementer.getBalance(player.getName()) +"$");
            }
            else
                System.out.println("console!");
        }
        if ( args[0].equals("spawn")){
            if (sender instanceof Player){
                VaultExchange.VaultExchangeGUI((Player) sender);
               /* Player player = (Player) sender;
                TraderMob test = new TraderMob(player.getLocation());
                WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
                world.addEntity(test); */
            }
            else
                System.out.println("console!");
        }
        return false;
    }
}
