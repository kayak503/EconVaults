package com.projectpalm.econvaults.econvaults.Events;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.GUI.GuiMethods;
import com.projectpalm.econvaults.econvaults.GUI.VaultGuiMethods;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

import java.util.UUID;

public class ClickEvent implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "Bank Teller")){

            UUID uuid = UUID.fromString(ChatColor.stripColor( e.getView().getItem(0).getLore().get(0)));
            VaultData vault = Data.GetVaultByUuid(uuid);

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BOLD+ "Close Shop")){
                player.closeInventory();
            }
            else{
                player.closeInventory();
                GuiMethods.GenerateBuySellInventory(e.getCurrentItem().getType(),vault,player, ChatColor.BOLD+"Bank Teller - Buy/Sell");
            }
            e.setCancelled(true);
        }
        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD+"Bank Teller - Buy/Sell")){

            UUID uuid = UUID.fromString(ChatColor.stripColor( e.getView().getItem(0).getLore().get(0)));
            VaultData vault = Data.GetVaultByUuid(uuid);

            if (e.getCurrentItem().getItemMeta().getDisplayName().equalsIgnoreCase(ChatColor.BOLD+"Back To Shop")){
                player.closeInventory();
                GuiMethods.GenerateInventory(GuiMethods.GetVaultItemList(vault),ChatColor.BOLD + "Bank Teller",player,true);
            }
            else{
                player.closeInventory();
                //GuiMethods.GenerateBuySellInventory(e.getCurrentItem().getType(),vault,player, ChatColor.BOLD+"Bank Teller - Buy/Sell");
            }
            e.setCancelled(true);
        }

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "Your Vaults")){

            String VaultName = e.getCurrentItem().getItemMeta().getDisplayName();
            VaultData Vault = Data.GetVaultByName(VaultName,player);
            if(Vault == null){
                player.sendMessage(ChatColor.YELLOW + "An Error Has occurred");
                e.setCancelled(true);
                player.closeInventory();
                return;
            }
            if(e.isLeftClick()){
                VaultGuiMethods.DisplayVaultProperties(player,Vault);
                player.closeInventory();
            }
            if(e.isRightClick()){
                VaultGuiMethods.EditVaultProperties(player,Vault);
                player.closeInventory();
            }
            e.setCancelled(true);
        }

//ChatColor.BOLD+"Bank Teller - Buy/Sell

    }
}
