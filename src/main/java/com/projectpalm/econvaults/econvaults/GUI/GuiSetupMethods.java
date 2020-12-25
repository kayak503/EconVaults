package com.projectpalm.econvaults.econvaults.GUI;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
public class GuiSetupMethods {
    public static ItemStack[] GetItemBuyList(ItemStack block, int[] quantity, int[] prices, String name){
        List<ItemStack> ReturnList = new ArrayList<>();
        String blockName = name;
        for (int i =0; i < prices.length; i++){
            ItemStack x = block;
            if(i <= quantity.length) {
                ItemMeta Filler_Meta = block.getItemMeta();
                if (quantity[i] != 1) {
                    Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy " + quantity[i] + " " + blockName +"s");
                }
                else {
                    Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy A " + blockName);
                }

                ArrayList<String> Filler_lore = new ArrayList<>();
                Filler_lore.add(ChatColor.WHITE + "Cost: " +ChatColor.GOLD + prices[i] + "$");
                Filler_Meta.setLore(Filler_lore);

                x.setItemMeta(Filler_Meta);
                ReturnList.add(x.clone());
            }
        }
        return ReturnList.toArray(new ItemStack[0]);
    }
    public static ItemStack[] GetItemBuyList(ItemStack block, int[] quantity, int[] prices){
        List<ItemStack> ReturnList = new ArrayList<>();
        String blockName = block.getI18NDisplayName();
        for (int i =0; i < prices.length; i++){
            ItemStack x = block;
            if(i <= quantity.length) {
                ItemMeta Filler_Meta = block.getItemMeta();
                if (quantity[i] != 1) {
                    Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy " + quantity[i] + " " + blockName +"s");
                }
                else {
                    Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy " + quantity[i] + " " + blockName);
                }

                ArrayList<String> Filler_lore = new ArrayList<>();
                Filler_lore.add(ChatColor.WHITE + "Cost: " +ChatColor.GOLD + prices[i] + "$");
                Filler_Meta.setLore(Filler_lore);

                x.setItemMeta(Filler_Meta);
                ReturnList.add(x.clone());
            }
        }
        return ReturnList.toArray(new ItemStack[0]);
    }
    public static ItemStack GetItemBuyList(ItemStack block, int price, String name){
        String blockName = name;
        ItemStack x = block;

        ItemMeta Filler_Meta = block.getItemMeta();
        Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy A " + blockName);
        ArrayList<String> Filler_lore = new ArrayList<>();
        Filler_lore.add(ChatColor.WHITE + "Cost: " +ChatColor.GOLD + price + "$");
        Filler_Meta.setLore(Filler_lore);

        x.setItemMeta(Filler_Meta);

        return x.clone();
    }
    public static ItemStack GetItemBuyList(ItemStack block, int price){
        String blockName = block.getI18NDisplayName();
        ItemStack x = block;

        ItemMeta Filler_Meta = block.getItemMeta();
        Filler_Meta.setDisplayName(ChatColor.GREEN + "Buy A " + blockName);
        ArrayList<String> Filler_lore = new ArrayList<>();
        Filler_lore.add(ChatColor.WHITE + "Cost: " +ChatColor.GOLD + price + "$");
        Filler_Meta.setLore(Filler_lore);

        x.setItemMeta(Filler_Meta);

        return x.clone();
    }

    public static ItemStack[] GetItemSellList(ItemStack block, int[] quantity, int[] prices, String name){
        List<ItemStack> ReturnList = new ArrayList<>();
        String blockName = name;
        for (int i =0; i < prices.length; i++){
            ItemStack x = block;
            if(i <= quantity.length) {
                ItemMeta Filler_Meta = block.getItemMeta();
                if (quantity[i] != 1) {
                    Filler_Meta.setDisplayName(ChatColor.RED + "Sell " + quantity[i] + " " + blockName +"s");
                }
                else {
                    Filler_Meta.setDisplayName(ChatColor.RED + "Sell A " + blockName);
                }

                ArrayList<String> Filler_lore = new ArrayList<>();
                Filler_lore.add(ChatColor.WHITE + "For: " +ChatColor.GOLD + prices[i] + "$");
                Filler_Meta.setLore(Filler_lore);

                x.setItemMeta(Filler_Meta);
                ReturnList.add(x.clone());
            }
        }
        return ReturnList.toArray(new ItemStack[0]);
    }
    public static ItemStack[] GetItemSellList(ItemStack block, int[] quantity, int[] prices){
        List<ItemStack> ReturnList = new ArrayList<>();
        String blockName = block.getI18NDisplayName();
        for (int i =0; i < prices.length; i++){
            ItemStack x = block;
            if(i <= quantity.length) {
                ItemMeta Filler_Meta = block.getItemMeta();
                if (quantity[i] != 1) {
                    Filler_Meta.setDisplayName(ChatColor.RED + "Sell " + quantity[i] + " " + blockName +"s");
                }
                else {
                    Filler_Meta.setDisplayName(ChatColor.RED + "Sell A " + blockName);
                }

                ArrayList<String> Filler_lore = new ArrayList<>();
                Filler_lore.add(ChatColor.WHITE + "For: " +ChatColor.GOLD + prices[i] + "$");
                Filler_Meta.setLore(Filler_lore);

                x.setItemMeta(Filler_Meta);
                ReturnList.add(x.clone());
            }
        }
        return ReturnList.toArray(new ItemStack[0]);
    }
    public static ItemStack GetItemSellList(ItemStack block, int price, String name){
        String blockName = name;
        ItemStack x = block;

        ItemMeta Filler_Meta = block.getItemMeta();
        Filler_Meta.setDisplayName(ChatColor.RED + "Sell A " + blockName);
        ArrayList<String> Filler_lore = new ArrayList<>();
        Filler_lore.add(ChatColor.WHITE + "For: " +ChatColor.GOLD + price + "$");
        Filler_Meta.setLore(Filler_lore);

        x.setItemMeta(Filler_Meta);

        return x.clone();
    }
    public static ItemStack GetItemSellList(ItemStack block, int price){
        String blockName = block.getI18NDisplayName();
        ItemStack x = block;

        ItemMeta Filler_Meta = block.getItemMeta();
        Filler_Meta.setDisplayName(ChatColor.RED + "Sell A " + blockName);
        ArrayList<String> Filler_lore = new ArrayList<>();
        Filler_lore.add(ChatColor.WHITE + "For: " +ChatColor.GOLD + price + "$");
        Filler_Meta.setLore(Filler_lore);

        x.setItemMeta(Filler_Meta);

        return x.clone();
    }

}
