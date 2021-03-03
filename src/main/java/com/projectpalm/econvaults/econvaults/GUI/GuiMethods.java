package com.projectpalm.econvaults.econvaults.GUI;

import com.mojang.authlib.GameProfile;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.SkullMeta;

import java.util.*;

public class GuiMethods {
    private static SortedMap<Character,String> Char64Map = new TreeMap<Character,String>();

    // Startup Function
    public static void StartUp(){
        Char64Map.put('a',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTY3ZDgxM2FlN2ZmZTViZTk1MWE0ZjQxZjJhYTYxOWE1ZTM4OTRlODVlYTVkNDk4NmY4NDk0OWM2M2Q3NjcyZSJ9fX0=");
        Char64Map.put('b',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNTBjMWI1ODRmMTM5ODdiNDY2MTM5Mjg1YjJmM2YyOGRmNjc4NzEyM2QwYjMyMjgzZDg3OTRlMzM3NGUyMyJ9fX0=");
        Char64Map.put('c',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYWJlOTgzZWM0NzgwMjRlYzZmZDA0NmZjZGZhNDg0MjY3NjkzOTU1MWI0NzM1MDQ0N2M3N2MxM2FmMThlNmYifX19");
        Char64Map.put('d',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5M2RjMGQ0YzVlODBmZjlhOGEwNWQyZmNmZTI2OTUzOWNiMzkyNzE5MGJhYzE5ZGEyZmNlNjFkNzEifX19");
        Char64Map.put('e',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZGJiMjczN2VjYmY5MTBlZmUzYjI2N2RiN2Q0YjMyN2YzNjBhYmM3MzJjNzdiZDBlNGVmZjFkNTEwY2RlZiJ9fX0=");
        Char64Map.put('f',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYjE4M2JhYjUwYTMyMjQwMjQ4ODZmMjUyNTFkMjRiNmRiOTNkNzNjMjQzMjU1OWZmNDllNDU5YjRjZDZhIn19fQ==");
        Char64Map.put('g',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMWNhM2YzMjRiZWVlZmI2YTBlMmM1YjNjNDZhYmM5MWNhOTFjMTRlYmE0MTlmYTQ3NjhhYzMwMjNkYmI0YjIifX19");
        Char64Map.put('h',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzFmMzQ2MmE0NzM1NDlmMTQ2OWY4OTdmODRhOGQ0MTE5YmM3MWQ0YTVkODUyZTg1YzI2YjU4OGE1YzBjNzJmIn19fQ==");
        Char64Map.put('i',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDYxNzhhZDUxZmQ1MmIxOWQwYTM4ODg3MTBiZDkyMDY4ZTkzMzI1MmFhYzZiMTNjNzZlN2U2ZWE1ZDMyMjYifX19");
        Char64Map.put('j',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2E3OWRiOTkyMzg2N2U2OWMxZGJmMTcxNTFlNmY0YWQ5MmNlNjgxYmNlZGQzOTc3ZWViYmM0NGMyMDZmNDkifX19");
        Char64Map.put('k',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTQ2MWIzOGM4ZTQ1NzgyYWRhNTlkMTYxMzJhNDIyMmMxOTM3NzhlN2Q3MGM0NTQyYzk1MzYzNzZmMzdiZTQyIn19fQ==");
        Char64Map.put('l',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzE5ZjUwYjQzMmQ4NjhhZTM1OGUxNmY2MmVjMjZmMzU0MzdhZWI5NDkyYmNlMTM1NmM5YWE2YmIxOWEzODYifX19");
        Char64Map.put('m',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDljNDVhMjRhYWFiZjQ5ZTIxN2MxNTQ4MzIwNDg0OGE3MzU4MmFiYTdmYWUxMGVlMmM1N2JkYjc2NDgyZiJ9fX0=");
        Char64Map.put('n',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMzViOGIzZDhjNzdkZmI4ZmJkMjQ5NWM4NDJlYWM5NGZmZmE2ZjU5M2JmMTVhMjU3NGQ4NTRkZmYzOTI4In19fQ==");
        Char64Map.put('o',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvZDExZGUxY2FkYjJhZGU2MTE0OWU1ZGVkMWJkODg1ZWRmMGRmNjI1OTI1NWIzM2I1ODdhOTZmOTgzYjJhMSJ9fX0=");
        Char64Map.put('p',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTBhNzk4OWI1ZDZlNjIxYTEyMWVlZGFlNmY0NzZkMzUxOTNjOTdjMWE3Y2I4ZWNkNDM2MjJhNDg1ZGMyZTkxMiJ9fX0=");
        Char64Map.put('q',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNDM2MDlmMWZhZjgxZWQ0OWM1ODk0YWMxNGM5NGJhNTI5ODlmZGE0ZTFkMmE1MmZkOTQ1YTU1ZWQ3MTllZDQifX19");
        Char64Map.put('r',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYTVjZWQ5OTMxYWNlMjNhZmMzNTEzNzEzNzliZjA1YzYzNWFkMTg2OTQzYmMxMzY0NzRlNGU1MTU2YzRjMzcifX19");
        Char64Map.put('s',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvM2U0MWM2MDU3MmM1MzNlOTNjYTQyMTIyODkyOWU1NGQ2Yzg1NjUyOTQ1OTI0OWMyNWMzMmJhMzNhMWIxNTE3In19fQ==");
        Char64Map.put('t',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMTU2MmU4YzFkNjZiMjFlNDU5YmU5YTI0ZTVjMDI3YTM0ZDI2OWJkY2U0ZmJlZTJmNzY3OGQyZDNlZTQ3MTgifX19");
        Char64Map.put('u',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNjA3ZmJjMzM5ZmYyNDFhYzNkNjYxOWJjYjY4MjUzZGZjM2M5ODc4MmJhZjNmMWY0ZWZkYjk1NGY5YzI2In19fQ==");
        Char64Map.put('v',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvY2M5YTEzODYzOGZlZGI1MzRkNzk5Mjg4NzZiYWJhMjYxYzdhNjRiYTc5YzQyNGRjYmFmY2M5YmFjNzAxMGI4In19fQ==");
        Char64Map.put('w',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvMjY5YWQxYTg4ZWQyYjA3NGUxMzAzYTEyOWY5NGU0YjcxMGNmM2U1YjRkOTk1MTYzNTY3ZjY4NzE5YzNkOTc5MiJ9fX0=");
        Char64Map.put('x',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNWE2Nzg3YmEzMjU2NGU3YzJmM2EwY2U2NDQ5OGVjYmIyM2I4OTg0NWU1YTY2YjVjZWM3NzM2ZjcyOWVkMzcifX19");
        Char64Map.put('y',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvYzUyZmIzODhlMzMyMTJhMjQ3OGI1ZTE1YTk2ZjI3YWNhNmM2MmFjNzE5ZTFlNWY4N2ExY2YwZGU3YjE1ZTkxOCJ9fX0=");
        Char64Map.put('z',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvOTA1ODJiOWI1ZDk3OTc0YjExNDYxZDYzZWNlZDg1ZjQzOGEzZWVmNWRjMzI3OWY5YzQ3ZTFlMzhlYTU0YWU4ZCJ9fX0=");
    }

    // Public

    public static ItemStack GetHeadChars(Character letter){
        letter = Character.toLowerCase(letter);
        String base64 = Char64Map.get(letter);
        if (base64 == null){
            return null;
        }
        return SkullCreator.itemFromBase64(base64);
    }

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
