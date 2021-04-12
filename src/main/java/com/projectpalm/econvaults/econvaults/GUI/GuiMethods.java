package com.projectpalm.econvaults.econvaults.GUI;

import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.ToolBox;
import dev.dbassett.skullcreator.SkullCreator;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

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
        Char64Map.put('<',"eyJ0ZXh0dXJlcyI6eyJTS0lOIjp7InVybCI6Imh0dHA6Ly90ZXh0dXJlcy5taW5lY3JhZnQubmV0L3RleHR1cmUvNzM3NjQ4YWU3YTU2NGE1Mjg3NzkyYjA1ZmFjNzljNmI2YmQ0N2Y2MTZhNTU5Y2U4YjU0M2U2OTQ3MjM1YmNlIn19fQ==");
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

    public static ItemStack[] GetVaultItemList(VaultData vault){
        // extract all map keys and return as a list of itemstacks with names
        List<ItemStack> itemList = new ArrayList<>();

        ItemStack BackButton = GuiMethods.GetHeadChars('<');
        ItemMeta BackButtonMeta = BackButton.getItemMeta();
        ArrayList<String> Filler_lore = new ArrayList<>();
//        Filler_lore.add("Exit out of " + vault.GetName());
        Filler_lore.add(ChatColor.MAGIC + vault.GetUuid().toString());
        BackButtonMeta.setLore(Filler_lore);
        BackButtonMeta.setDisplayName(ChatColor.BOLD+"Close Shop");
        BackButton.setItemMeta(BackButtonMeta);
        itemList.add(BackButton);

        for(Material key: vault.GetContentsMap().keySet()){
            ItemStack block =  new ItemStack(key);
            ItemMeta meta = block.getItemMeta();
            meta.setDisplayName(ChatColor.GOLD +"Buy/Sell "+ ToolBox.toTitleCase(block.getType().toString()));
            block.setItemMeta(meta);
            itemList.add(block);
        }

        return itemList.toArray(new ItemStack[itemList.size()]);
        //itemList.add();

    }

    public static void GenerateInventory(ItemStack[] items, String name, Player player, Boolean FirstSlotIsBackButton){
        int ROWSIZE = 9;

        int rows = (int) ((items.length/ROWSIZE) +0.999999);
        if (rows == 0) {rows = 1;}
        int inventorySize = rows*ROWSIZE;
        List<ItemStack> itemList = Arrays.asList(items);
        if (items.length >= inventorySize - 1){
            Inventory gui = Bukkit.createInventory(player, inventorySize, name);
            gui.setContents(items);
            player.openInventory(gui);
            return;
        }
        int itemListLength =  items.length;
        int inventorySizeCalc = inventorySize;
        int startSlot = 0;
        if (FirstSlotIsBackButton){
            inventorySizeCalc--;
            startSlot++;
        }


        if ((inventorySizeCalc - itemListLength )%2 == 0){
            int NullAddition = ((inventorySizeCalc - itemListLength)/2);
            ItemStack[] itemsFinal = new ItemStack[items.length+NullAddition];

            for (int i =0; i < items.length; i++){
                itemsFinal[i+ NullAddition] = items[i];
            }

            if (FirstSlotIsBackButton){
                itemsFinal[0] = items[0];
                itemsFinal[NullAddition] = null;
            }
            Inventory gui = Bukkit.createInventory(player, inventorySize, name);
            gui.setContents(itemsFinal);
            player.openInventory(gui);
            }
        else {
            int NullAddition = (((inventorySizeCalc - itemListLength)+1)/2);
            ItemStack[] itemsFinal = new ItemStack[items.length+(NullAddition*2)+5];

            for (int i =0; i < items.length; i++){
                itemsFinal[i+ NullAddition] = items[i];
            }

            if (FirstSlotIsBackButton){
                itemsFinal[0] = items[0];
                itemsFinal[NullAddition] = null;
            }
            itemsFinal[items.length+NullAddition-1] = null;
            itemsFinal[(items.length+(NullAddition*2)+5)-1] = items[items.length-1];


            Inventory gui = Bukkit.createInventory(player, inventorySize+ROWSIZE, name);
            gui.setContents(itemsFinal);
            player.openInventory(gui);
        }
    }

    public static void GenerateBuySellInventory(Material material, VaultData vault, Player player,String name){

        int VaultSpaceLeft;
        int VaultQuantityOfItem;
        int PlayerBankAccount;

        int[] quantities = {1,5,10,50,100};
        ItemStack[] itemList = new ItemStack[9*5];

        ItemStack BackButton = GuiMethods.GetHeadChars('<');
            ItemMeta BackButtonMeta = BackButton.getItemMeta();
                ArrayList<String> lore = new ArrayList<>();
                lore.add(ChatColor.MAGIC + vault.GetUuid().toString());
            BackButtonMeta.setLore(lore);
            BackButtonMeta.setDisplayName(ChatColor.BOLD+"Back To Shop");
        BackButton.setItemMeta(BackButtonMeta);
        itemList[0] = BackButton;

        ItemStack Block = new ItemStack(material);
        int Price =  EconVaults.getInstance.getConfig().getInt("VaultExchangeItems." + material.toString());
        // gen Buy blocks
        for (int i =0; i < quantities.length; i++){
            int quantity = quantities[i];
            ItemMeta Meta = Block.getItemMeta();

            ArrayList<String> Lore = new ArrayList<>();

            if(EconVaults.getInstance.economyImplementer.has(player.getName(),Price*quantity)) {
                if (quantity != 1) {
                    Meta.setDisplayName(ChatColor.GREEN + "Buy " + quantity + " " + material.name() + "s");
                } else {
                    Meta.setDisplayName(ChatColor.GREEN + "Buy A " + material.name());
                }
            }
            else {
                if (quantity != 1) {
                    Meta.setDisplayName(ChatColor.STRIKETHROUGH + "Buy " + quantity + " " + material.name() + "s" + ChatColor.STRIKETHROUGH  );
                    Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY +
                            "Not Enough Money To Buy");
                } else {
                    Meta.setDisplayName(ChatColor.STRIKETHROUGH + "Buy A " + material.name());
                    Lore.add( ChatColor.BOLD + "" + ChatColor.GRAY +
                            "Not Enough Money To Buy");
                }
            }

            Lore.add(ChatColor.WHITE + "Cost: " +ChatColor.GOLD + quantity*Price + "$");

            Meta.setLore(Lore);
            Block.setItemMeta(Meta);

            itemList[9+(i*2)] = Block.clone();
        }

        int numOfItem = 0;
        ItemStack[] playerInventory = player.getInventory().getContents();
        for (ItemStack item: playerInventory){
            if (item != null) {
                if (item.getType().equals(material)) {
                    numOfItem += item.getAmount();
                }
            }
        }

        for (int i =0; i < quantities.length; i++){
            int quantity = quantities[i];
            ItemMeta Meta = Block.getItemMeta();

            ArrayList<String> Lore = new ArrayList<>();

            if(numOfItem >= quantity) {
                if (quantity != 1) {
                    Meta.setDisplayName(ChatColor.AQUA + "Sell " + quantity + " " + material.name() + "s");
                } else {
                    Meta.setDisplayName(ChatColor.AQUA + "Sell A " + material.name());
                }
            }
            else {
                if (quantity != 1) {
                    Meta.setDisplayName(ChatColor.STRIKETHROUGH + "Sell " + quantity + " " + material.name() + "s" + ChatColor.STRIKETHROUGH  );
                    Lore.add(ChatColor.BOLD + "" + ChatColor.GRAY +
                            "Sufficient Quantity not in inventory");
                } else {
                    Meta.setDisplayName(ChatColor.STRIKETHROUGH + "Sell A " + material.name());
                    Lore.add( ChatColor.BOLD + "" + ChatColor.GRAY +
                            "Sufficient Quantity not in inventory");
                }
            }

            Lore.add(ChatColor.WHITE + "Sell For: " +ChatColor.GOLD + quantity*Price + "$");

            Meta.setLore(Lore);
            Block.setItemMeta(Meta);

            itemList[27+(i*2)] = Block.clone();
        }


        Inventory gui = Bukkit.createInventory(player, 9*5, name);
        gui.setContents(itemList);
        player.openInventory(gui);




    }
    }
