package com.projectpalm.econvaults.econvaults.GUI;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

// deprecated to be deleted at a later date
public class VaultExchange {
    public static void VaultExchangeGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 27, ChatColor.BOLD + "Bank Teller");

        ItemStack DiamondBlock = new ItemStack(Material.DIAMOND_BLOCK);
            ItemMeta DiamondBlock_Meta = DiamondBlock.getItemMeta();
            DiamondBlock_Meta.setDisplayName("Buy/Sell Diamonds");
            DiamondBlock.setItemMeta(DiamondBlock_Meta);

        ItemStack NetheriteBlock  = new ItemStack(Material.NETHERITE_BLOCK);
            ItemMeta NetheriteBlock_Meta = NetheriteBlock.getItemMeta();
            NetheriteBlock_Meta.setDisplayName("Buy/Sell Netherite");
            NetheriteBlock.setItemMeta(NetheriteBlock_Meta);

        ItemStack GoldBock = new ItemStack(Material.GOLD_BLOCK);
            ItemMeta GoldBock_Meta = GoldBock.getItemMeta();
            GoldBock_Meta.setDisplayName("Buy/Sell Gold");
            GoldBock.setItemMeta(GoldBock_Meta);

        ItemStack IronBock = new ItemStack(Material.IRON_BLOCK);
            ItemMeta IronBock_Meta = IronBock.getItemMeta();
            IronBock_Meta.setDisplayName("Buy/Sell Iron");
            IronBock.setItemMeta(IronBock_Meta);

        ItemStack[] GuiItems = {
                null, null, null, null, null, null, null, null, null,
                null, NetheriteBlock, null, DiamondBlock, null, GoldBock, null, IronBock, null,
                null, null, null, null, null, null, null, null, null,
                };

        //gui.setContents(GuiSetupMethods.GetItemBuyList(new ItemStack(Material.DIAMOND_BLOCK), new int[]{1, 5, 10, 20, 50, 75, 100,500, 1000}, new int[]{1, 5, 10, 20, 50, 75, 100,500, 1000}, "Diamond Block"));
        gui.setContents(GuiItems);
        player.openInventory(gui);

    }
    public static void VaultExchangeDiamondGUI(Player player){
        Inventory gui = Bukkit.createInventory(player, 27, ChatColor.BOLD + "Bank Teller - Diamonds");

        ItemStack[] Buys = GuiMethods.GetItemBuyList(new ItemStack(Material.DIAMOND_BLOCK), new int[]{1, 5, 10, 50, 100}, new int[]{40 , 200 , 400, 2000, 4000}, "Diamond Block");

        ItemStack[] Sells = GuiMethods.GetItemSellList(new ItemStack(Material.DIAMOND_BLOCK), new int[]{1, 5, 10, 50, 100}, new int[]{40 , 200 , 400, 2000, 4000}, "Diamond Block");

        ItemStack[] GuiItems = {
                Buys[0], null, Buys[1], null, Buys[2], null, Buys[3], null, Buys[4],
                null, null, null, null, null, null, null, null, null,
                Sells[0], null, Sells[1], null, Sells[2], null, Sells[3], null, Sells[4]

        };

        gui.setContents(GuiItems);
        player.openInventory(gui);
    }
}
