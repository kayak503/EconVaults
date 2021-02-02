package com.projectpalm.econvaults.econvaults.Events;

import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.GUI.VaultExchange;
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

    }
}
