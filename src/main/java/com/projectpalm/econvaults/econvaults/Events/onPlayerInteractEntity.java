package com.projectpalm.econvaults.econvaults.Events;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.GUI.GuiMethods;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEntityEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

public class onPlayerInteractEntity implements Listener {
        @EventHandler
        public void onPlayerInteractEntity (PlayerInteractEntityEvent event) {
            Entity entity = event.getRightClicked();
            Player player = event.getPlayer();
            if (entity.getType().equals(EntityType.VILLAGER)){
                if (EconVaults.getInstance.tab.isInTellerMap(entity.getUniqueId())){
                    VaultData vault = Data.GetVaultByUuid(entity.getUniqueId());
                    if (vault != null){
                        //vault
                        //ItemStack[] ItemStackArray = GuiMethods.GetVaultItemList(vault);
                        //Inventory gui = Bukkit.createInventory(player, 27, ChatColor.BOLD + "Bank Teller");
                        //gui.setContents(ItemStackArray);
                        //player.openInventory(gui);
                        GuiMethods.GenerateInventory(GuiMethods.GetVaultItemList(vault),ChatColor.BOLD + "Bank Teller",player,true);
                    }
                }



            }
    }
}
