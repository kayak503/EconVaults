package com.projectpalm.econvaults.econvaults.Events;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.GUI.VaultExchange;
import com.projectpalm.econvaults.econvaults.GUI.VaultGuiMethods;
import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;

public class ClickEvent implements Listener {
    @EventHandler
    public void ClickEvent(InventoryClickEvent e){

        Player player = (Player) e.getWhoClicked();

        if(e.getView().getTitle().equalsIgnoreCase(ChatColor.BOLD + "Bank Teller")){

            switch (e.getCurrentItem().getType()){
                case DIAMOND_BLOCK:
                    player.closeInventory();
                    VaultExchange.VaultExchangeDiamondGUI(player);
                    break;
                case NETHERITE_BLOCK:
                    player.closeInventory();

                    break;
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

    }
}
