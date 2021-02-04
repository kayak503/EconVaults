package com.projectpalm.econvaults.econvaults.commands;

import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;


public class money  implements CommandExecutor {
    private final EconVaults plugin = EconVaults.getInstance;
    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (args.length == 0 || args[0].equalsIgnoreCase("?")){
            if (sender instanceof Player){
                Player player = (Player) sender;
                sender.sendMessage("Balance : " + plugin.economyImplementer.getBalance(player.getName()) + "$");
            }
            else{
                System.out.println(ToolBox.CautionBase + "Command not supported in console!");
            }
        }

        else if ( args[0].equalsIgnoreCase("SetAccount")){ // SetAccount %PlayerName% %NewValue%
            if(args.length == 2){
                if (sender instanceof Player) {
                    if (!ToolBox.IsNumeric(args[1])) {
                        sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[1] + "], expected number got string ");
                        return false;
                    }
                    Player player = (Player) sender;
                    int newValue = ( (int) Double.parseDouble(args[1]) ) - (int) plugin.economyImplementer.getBalance(player.getName()) ;
                    plugin.economyImplementer.depositPlayer(player.getName(), newValue);
                    sender.sendMessage(ChatColor.GREEN + "New Account Value : " + Double.parseDouble(args[1]) + "$");

                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "Please add player username in command, (ie) money SetAccount %playerName% %value%" );
                }
            }
            else if (args.length == 3){
                if (ToolBox.IsNumeric(args[1])) {
                    sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[1] + "], expected String got Number ");
                    return false;
                }
                if (!ToolBox.IsNumeric(args[2])) {
                    sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[2] + "], expected Number got String ");
                    return false;
                }


                int newValue = ( (int) Double.parseDouble(args[2]) ) - (int) plugin.economyImplementer.getBalance(args[1]) ;
                if ( plugin.economyImplementer.depositPlayer(args[1], newValue).transactionSuccess() == false ){
                    sender.sendMessage(ChatColor.YELLOW + "Username not fond, Please note usernames are case sensitive");
                    return false;
                }
                sender.sendMessage(ChatColor.GREEN + "New Account Value : " + Double.parseDouble(args[2]) + "$");
            }
            else {
                sender.sendMessage(ChatColor.YELLOW + "Invalid parameters, expected 2 Or 3, Got " + args.length );
            }
        }

        else if (args[0].equalsIgnoreCase("Account")){
            if(args.length == 2){
                    if (ToolBox.IsNumeric(args[1])) {
                        sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[1] + "], expected string got number ");
                        return false;
                    }
                    Player player = Bukkit.getPlayer(args[1]);
                    if (player == null){
                    sender.sendMessage(ChatColor.YELLOW + "Player "+ args[1] + ", is not online and dose not have an account. Note: Player names are cap sensitive." );
                    return false;
                }
                    sender.sendMessage(args[1] + " Account : " + plugin.economyImplementer.getBalance(args[1]) + "$");


            }
            else {
                sender.sendMessage(ChatColor.YELLOW + "Invalid parameters, expected 2, Got " + args.length );
            }
        }

        else if (args[0].equalsIgnoreCase("Send")){ // money Send %UserName% %amount%
            if(args.length == 3){
                if (sender instanceof Player) {
                    // data check
                    if (ToolBox.IsNumeric(args[1])) {
                        sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[1] + "], expected String got Number ");
                        return false;
                    }
                    if (!ToolBox.IsNumeric(args[2])) {
                        sender.sendMessage(ChatColor.YELLOW + "Invalid parameter [" + args[2] + "], expected Number got String ");
                        return false;
                    }

                    // vars
                    Player SenderPlayer = (Player) sender;
                    Player ReceiverPlayer = Bukkit.getPlayer(args[1]);
                    int amount = (int) Double.parseDouble(args[2]) ;

                    // make sure amount is above 1 coin
                    if (amount <= 0){
                        sender.sendMessage(ChatColor.YELLOW + "Perimeter 2 must be greater than 0$");
                        return false;
                    }
                    // make sure both players are online
                    if (ReceiverPlayer == null){
                        sender.sendMessage(ChatColor.YELLOW + "Receiving Player "+ args[1] + ", Does not exist or is not online. Note: Player names are cap sensitive." );
                        return false;
                    }
                    // make sure SenderPlayer has enough coin
                    if (!plugin.economyImplementer.has(SenderPlayer.getName(), amount)){
                        sender.sendMessage(ChatColor.YELLOW + "You do not have enough Coins to send "+ Double.parseDouble(args[2]) + "$ to " + ReceiverPlayer.getName()  );
                        return false;
                    }

                    if (plugin.economyImplementer.withdrawPlayer(SenderPlayer.getName(), amount).transactionSuccess() == true){
                        if (plugin.economyImplementer.depositPlayer(ReceiverPlayer.getName(), amount).transactionSuccess() == true){
                            sender.sendMessage(ChatColor.GREEN + "You have successfully sent "+ Double.parseDouble(args[2]) + "$ to " + ReceiverPlayer.getName()  );
                            ReceiverPlayer.sendMessage(ChatColor.GREEN + SenderPlayer.getName() +" has sent you " + Double.parseDouble(args[2]) + "$");
                        }
                        else {
                            sender.sendMessage(ChatColor.RED + "An Error has occurred, your balance has not changed ");
                            plugin.economyImplementer.depositPlayer(SenderPlayer.getName(), amount);
                        }
                    }
                    else {
                        sender.sendMessage(ChatColor.RED + "An Error has occurred your balance has not changed ");
                    }
                }
                else {
                    sender.sendMessage(ChatColor.YELLOW + "This is a player exclusive Command " );
                }
            }
            else {
                sender.sendMessage(ChatColor.YELLOW + "Invalid parameters, expected 2, Got " + (args.length -1)  );
            }
        } //require both players online


        return false;
    }
}
