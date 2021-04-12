package com.projectpalm.econvaults.econvaults.GUI;
import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.BookMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.*;


public class VaultGuiMethods { //
   // Creates and sends a GUI of A players Vaults that are owned by them.
   public static void ShowVaults(Player player){
      VaultData[] playerVaults = Data.GetAllPlayerVaults(player);
      if (playerVaults == null){
         player.sendMessage("You have no vaults");
         return;
      }

      List ItemStackVaults = new ArrayList<ItemStack>();
      for (int i=0; i < playerVaults.length; i++){
         VaultData vault = playerVaults[i];
         ItemStack vaultHead = GuiMethods.GetHeadChars(vault.GetName().charAt(0));
         ItemMeta vaultHeadMeta = vaultHead.getItemMeta();
         ArrayList<String> Filler_lore = new ArrayList<>();
         Filler_lore.add(ChatColor.DARK_GREEN +"Left Click to View Vault properties");
         Filler_lore.add(ChatColor.DARK_RED +"Right Click to Edit Vault properties");
         vaultHeadMeta.setLore(Filler_lore);
         vaultHeadMeta.setDisplayName(vault.GetName());
         vaultHead.setItemMeta(vaultHeadMeta);
         ItemStackVaults.add(vaultHead);
      }



      ItemStack[] ItemStackVaultsArray = new ItemStack[ItemStackVaults.size()];
      ItemStackVaultsArray = (ItemStack[]) ItemStackVaults.toArray(ItemStackVaultsArray);

      GuiMethods.GenerateInventory(ItemStackVaultsArray,ChatColor.BOLD + "Your Vaults",player,false);

   }
   // adds a new vault for the player with the use of a book
   public static void AddNewVault(Player player){
      ItemStack book = player.getInventory().getItemInMainHand();
      if (book.getType().equals(Material.WRITTEN_BOOK)){
         BookMeta bookMeta = (BookMeta) book.getItemMeta();
         String creator =  bookMeta.getAuthor();
         String name = bookMeta.getTitle();
         Integer[][] VaultPosition = new Integer[0][];
         Integer[] TellerPosition = new Integer[0];
         String Team = "";
         String[] Owners;
         Map<Material,Integer> Contents = new HashMap<>();

         String BookContent = bookMeta.getPages().toString().replace("[", "").replace("]","");

         List usernames = new ArrayList<String>();
         usernames.add(creator);

         for (String string: BookContent.split(";")) {
            String[] line = string.split(":");

            if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("VaultPosition")){
               List cords = new ArrayList<Integer>();
               for ( String content: line[1].split(" ")){
                  if(ToolBox.IsNumeric(content)){
                     cords.add(Integer.parseInt(content));
                  }
                  else {
                     if (!content.equalsIgnoreCase("")) {
                        player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected Integer, got" + content);
                     }
                  }
               }
               if (cords.size() != 6){
                  player.sendMessage(ChatColor.RED + "Congruency Error: " + line[0] + " expected 6 Integers, got " + cords.size() );
                  return;
               }
               VaultPosition = new Integer[2][3];
               VaultPosition[0][0] = (Integer) cords.get(0);
               VaultPosition[0][1] = (Integer) cords.get(1);
               VaultPosition[0][2] = (Integer) cords.get(2);

               VaultPosition[1][0] = (Integer) cords.get(3);
               VaultPosition[1][1] = (Integer) cords.get(4);
               VaultPosition[1][2] = (Integer) cords.get(5);


            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("TellerPosition")){
               List cords = new ArrayList<Integer>();
               for ( String content: line[1].split(" ")){
                  if(ToolBox.IsNumeric(content)){
                     cords.add(Integer.parseInt(content));
                  }
                  else {
                     if (!content.equalsIgnoreCase("")) {
                        player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected Integer, got" + content);
                     }
                  }
               }
               if (cords.size() != 3){
                  player.sendMessage(ChatColor.RED + "Congruency Error: " + line[0] + " expected 3 Integers, got " + cords.size() );
                  return;
               }
               TellerPosition = new Integer[]{(Integer) cords.get(0),(Integer) cords.get(1),(Integer) cords.get(2)};


            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Team")){
               if (ToolBox.IsNumeric(line[1])){
                  player.sendMessage(ChatColor.RED + "Syntax Error: " + line[0] + " Expected String, got Number");
                  return;
               }
               Team = line[1].replaceAll("\\s+", "");
            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Owners")){
               for ( String content: line[1].split(" ")){
                  if(!ToolBox.IsNumeric(content)){
                     if (!content.equalsIgnoreCase("")) {
                        if (!content.equalsIgnoreCase(creator)) {
                           usernames.add(content);
                        }
                     }
                  }
                  else {
                     player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected String, got Number");
                  }
               }
            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Contents")){
               for ( String Item: line[1].split(" ")){
                  if(!ToolBox.IsNumeric(Item)){
                     if (!Item.equalsIgnoreCase("")) {
                        if (!Item.equalsIgnoreCase(creator)) {
                           EconVaults.getInstance.getConfig().getConfigurationSection("VaultExchangeItems").getKeys(false).forEach( ApprovedBlock ->{
                              if (ApprovedBlock.equalsIgnoreCase(Item)){
                                 try {
                                    Contents.put(Material.getMaterial(ApprovedBlock),0);
                                 } catch (Exception e) {
                                    player.sendMessage(ChatColor.RED +"Error item "+ Item + "could not be converted to Material");
                                    System.out.println(ToolBox.ErrorBase + e);
                                    return;
                                 }

                              }
                           });  //getStringList("VaultExchangeItems").


                        }
                     }
                  }
                  else {
                     player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected String, got Number");
                  }
               }
            }

         }

         Owners = new String[usernames.size()];
         Owners = (String[]) usernames.toArray(Owners);

         if (Data.GetVaultByName(name,player) != null){
            player.sendMessage(ChatColor.RED + "Name Error: You already have a vault called " + name + ". To see your full list of vaults do: /econvaults vaults ");
            return;
         }
         if (Contents.size() ==0){
            player.sendMessage(ChatColor.RED + "Contents Error: You have no contents for your vault");
            return;
         }

         VaultData newVault = new VaultData();
         newVault.SetName(name);
         newVault.SetVaultPosition(VaultPosition);
         newVault.SetTellerPosition(TellerPosition);
         newVault.SetTeam(Team);
         newVault.SetOwners(Owners);
         newVault.GenUuid();
         newVault.SetContentsMap(Contents);
         Data.AddNewVault(newVault);
         EconVaults.getInstance.tab.spawnTeller(newVault);

         if (Data.GetVaultByName(name,player) != null){
            player.sendMessage(ChatColor.GREEN + newVault.GetName() +" Was successfully created ");
            player.getInventory().setItemInMainHand(null);         }
         else {
            player.sendMessage(ChatColor.RED + " Vault creation failed, Something went wrong... :( ");
         }

      }
   }
   // edits an existing vault for the player with the use of a book
   public static void EditVault(Player player){
      ItemStack book = player.getInventory().getItemInMainHand();
      if (book.getType().equals(Material.WRITTEN_BOOK)){
         BookMeta bookMeta = (BookMeta) book.getItemMeta();
         String name = bookMeta.getTitle();
         VaultData MatchingVaults = Data.GetVaultByName(name,player);
         if (MatchingVaults == null){
            player.sendMessage(ChatColor.RED+"Vault Update Failed, No matching vaults");
            return;
         }

         String creator =  bookMeta.getAuthor();
         Integer[][] VaultPosition = new Integer[0][];
         Integer[] TellerPosition = new Integer[0];
         String Team = "";
         String[] Owners;



         Map<Material,Integer> Contents = new HashMap<>();

         String BookContent = bookMeta.getPages().toString().replace("[", "").replace("]","");

         List usernames = new ArrayList<String>();
         usernames.add(creator);

         for (String string: BookContent.split(";")) {
            String[] line = string.split(":");

            if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("VaultPosition")){
               List cords = new ArrayList<Integer>();
               for ( String content: line[1].split(" ")){
                  if(ToolBox.IsNumeric(content)){
                     cords.add(Integer.parseInt(content));
                  }
                  else {
                     if (!content.equalsIgnoreCase("")) {
                        player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected Integer, got" + content);
                     }
                  }
               }
               if (cords.size() != 6){
                  player.sendMessage(ChatColor.RED + "Congruency Error: " + line[0] + " expected 6 Integers, got " + cords.size() );
                  return;
               }
               VaultPosition = new Integer[2][3];
               VaultPosition[0][0] = (Integer) cords.get(0);
               VaultPosition[0][1] = (Integer) cords.get(1);
               VaultPosition[0][2] = (Integer) cords.get(2);

               VaultPosition[1][0] = (Integer) cords.get(3);
               VaultPosition[1][1] = (Integer) cords.get(4);
               VaultPosition[1][2] = (Integer) cords.get(5);


            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("TellerPosition")){
               List cords = new ArrayList<Integer>();
               for ( String content: line[1].split(" ")){
                  if(ToolBox.IsNumeric(content)){
                     cords.add(Integer.parseInt(content));
                  }
                  else {
                     if (!content.equalsIgnoreCase("")) {
                        player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected Integer, got" + content);
                     }
                  }
               }
               if (cords.size() != 3){
                  player.sendMessage(ChatColor.RED + "Congruency Error: " + line[0] + " expected 3 Integers, got " + cords.size() );
                  return;
               }
               TellerPosition = new Integer[]{(Integer) cords.get(0),(Integer) cords.get(1),(Integer) cords.get(2)};


            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Team")){
               if (ToolBox.IsNumeric(line[1])){
                  player.sendMessage(ChatColor.RED + "Syntax Error: " + line[0] + " Expected String, got Number");
                  return;
               }
               Team = line[1].replaceAll("\\s+", "");
            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Owners")){
               for ( String content: line[1].split(" ")){
                  if(!ToolBox.IsNumeric(content)){
                     if (!content.equalsIgnoreCase("")) {
                        if (!content.equalsIgnoreCase(creator)) {
                           usernames.add(content);
                        }
                     }
                  }
                  else {
                     player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected String, got Number");
                  }
               }
            }
            else if(line[0].replaceAll("\\s+", "").equalsIgnoreCase("Contents")){
               for ( String Item: line[1].split(" ")){
                  if(!ToolBox.IsNumeric(Item)){
                     if (!Item.equalsIgnoreCase("")) {
                        if (!Item.equalsIgnoreCase(creator)) {
                           EconVaults.getInstance.getConfig().getConfigurationSection("VaultExchangeItems").getKeys(false).forEach( ApprovedBlock ->{
                              if (ApprovedBlock.equalsIgnoreCase(Item)){
                                 try {
                                    Contents.put(Material.getMaterial(ApprovedBlock),0);
                                 } catch (Exception e) {
                                    player.sendMessage(ChatColor.RED +"Error item "+ Item + "could not be converted to Material");
                                    System.out.println(ToolBox.ErrorBase + e);
                                    return;
                                 }

                              }
                           });  //getStringList("VaultExchangeItems").


                        }
                     }
                  }
                  else {
                     player.sendMessage(ChatColor.YELLOW + "Syntax Error: " + line[0] + " Expected String, got Number");
                  }
               }
            }

         }

         Owners = new String[usernames.size()];
         Owners = (String[]) usernames.toArray(Owners);

         if (Contents.size() ==0){
            player.sendMessage(ChatColor.RED + "Contents Error: You have no contents for your vault");
            return;
         }

         EconVaults.getInstance.tab.killTeller(MatchingVaults.GetUuid());
         MatchingVaults.SetName(name);
         MatchingVaults.SetVaultPosition(VaultPosition);
         MatchingVaults.SetTellerPosition(TellerPosition);
         MatchingVaults.SetTeam(Team);
         MatchingVaults.SetOwners(Owners);
         MatchingVaults.GenUuid();
         MatchingVaults.SetContentsMap(Contents);
         Data.UpdateVault(MatchingVaults);


         if (Data.GetVaultByName(name,player) != null){
            EconVaults.getInstance.tab.killTeller(MatchingVaults.GetUuid());
            player.sendMessage(ChatColor.GREEN + MatchingVaults.GetName() +" Was successfully Updated");
            player.getInventory().setItemInMainHand(null);
            EconVaults.getInstance.tab.spawnTeller(MatchingVaults);
         }
         else {
            player.sendMessage(ChatColor.RED + " Vault Update failed, Something went wrong... :( ");
         }

      }
   }

   // gives the player a vaults data with the use of a written book
   public static void DisplayVaultProperties(Player player, VaultData vault){
      if (player.getInventory().getItemInMainHand().getType() != Material.AIR){
         player.sendMessage(ChatColor.YELLOW + "Your main Hand Must be free to use this Command");
         return;
      }

      ItemStack book = new ItemStack(Material.WRITTEN_BOOK);
      BookMeta im = (BookMeta) book.getItemMeta();

      im.setDisplayName(vault.GetName());
      im.setAuthor("EconVaults");
      im.setTitle(vault.GetName());
      ArrayList<String> lore = new ArrayList<String>();

      Integer[][] VaultPositionInt = vault.GetVaultPosition();
      String VaultPosition = "VaultPosition: " + VaultPositionInt[0][0] + " " + VaultPositionInt[0][1] + " " + VaultPositionInt[0][2]
              + " " + VaultPositionInt[1][0] + " " + VaultPositionInt[1][1] + " " + VaultPositionInt[1][2] +";";



      String Booktext = VaultPosition + "\nTellerPosition: " + Arrays.toString(vault.GetTellerPosition()).replace(",", "").replace("[","").replace("]","") + ";"
              + "\nTeam: "+ vault.GetTeam() + ";"
              + "\nOwners: " + Arrays.toString(vault.GetOwners()).replace(",", "").replace("[","").replace("]","")  + ";"
              + "\nContents: " + Arrays.toString(vault.GetContentsMap().keySet().toArray()).replace(",", "").replace("[","").replace("]","")  + ";"; // page limit 19*14

      im.setPages(Booktext);
      book.setItemMeta(im);
      player.getInventory().setItemInMainHand(book);
   }
   // gives the player a vaults data with the use of a book and quill
   public static void EditVaultProperties(Player player, VaultData vault){
      if (player.getInventory().getItemInMainHand().getType() != Material.AIR){
         player.sendMessage(ChatColor.YELLOW + "Your main Hand Must be free to use this Command");
         return;
      }

      ItemStack book = new ItemStack(Material.WRITABLE_BOOK);
      BookMeta im = (BookMeta) book.getItemMeta();

      im.setDisplayName(vault.GetName());
      im.setAuthor("EconVaults");
      im.setTitle(vault.GetName());
      ArrayList<String> lore = new ArrayList<String>();

      Integer[][] VaultPositionInt = vault.GetVaultPosition();
      String VaultPosition = "VaultPosition: " + VaultPositionInt[0][0] + " " + VaultPositionInt[0][1] + " " + VaultPositionInt[0][2]
              + " " + VaultPositionInt[1][0] + " " + VaultPositionInt[1][1] + " " + VaultPositionInt[1][2] +";";



      String Booktext = VaultPosition + "\nTellerPosition: " + Arrays.toString(vault.GetTellerPosition()).replace(",", "").replace("[","").replace("]","") + ";"
              + "\nTeam: "+ vault.GetTeam() + ";"
              + "\nOwners: " + Arrays.toString(vault.GetOwners()).replace(",", "").replace("[","").replace("]","")  + ";"
              + "\nContents: " + Arrays.toString(vault.GetContentsMap().keySet().toArray()).replace(",", "").replace("[","").replace("]","")  + ";"; // page limit 19*14

      im.setPages(Booktext);
      book.setItemMeta(im);
      player.getInventory().setItemInMainHand(book);

   }


}
