package com.projectpalm.econvaults.econvaults.commands;

import com.jayway.jsonpath.JsonPath;
import com.projectpalm.econvaults.econvaults.Errors;
import com.projectpalm.econvaults.econvaults.data;
import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.json.simple.parser.ParseException;

import java.io.IOException;
import java.util.List;
import java.util.regex.Pattern;

public class TriggerCommand implements CommandExecutor {
    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    public boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
         if (args.length == 0 || args[0].equals("?")){
             sender.sendMessage(ChatColor.GOLD +"Welcome to Econ Vaults! bellow are the basic commands to get you going");
             sender.sendMessage(ChatColor.GOLD +"<t> Vaults ; shows the list of created vaults ");
             sender.sendMessage(ChatColor.GOLD +"<t> setVault <name> <x> <y> <z> <x> <y> <z> ; creates a new vault");
             sender.sendMessage(ChatColor.GOLD +"<t> getVault <name>  ; returns vault position");
             sender.sendMessage(ChatColor.GOLD +"<t> setTrader <name> <x> <y> <z> ; sets to position of the vault trader ");
             sender.sendMessage(ChatColor.GOLD +"<t> getTrader <name>  ; returns trader position");
         }
         else if (args[0].toLowerCase().equals("vaults")){
             try {
                 String FullJson = data.readVaultData();
                 List<String> keyInfo = JsonPath.read(FullJson, "$..Name");
                 if(keyInfo.toString().isEmpty()){
                     sender.sendMessage("No Vaults have been set up yet");
                 }
                 else {
                     sender.sendMessage(keyInfo.toString());
                 }

             } catch (IOException e) {
                 e.printStackTrace();
                 sender.sendMessage(ChatColor.RED+"[Econ Vaults] Error 4: please contact your server admins");
             } catch (ParseException e) {
                 e.printStackTrace();
                 sender.sendMessage(ChatColor.RED+"[Econ Vaults] Error 4: please contact your server admins");
             }
         }

         else if (args[0].toLowerCase().equals("setvault")){

             // error checking (make sure all input is valid)
             String errorMSG ="The following values are missing or incorrect:";
             Boolean ThrowError = false;
             String[] ErrorArray ={" <name>"," <x1>"," <y1>"," <z1>"," <x2>"," <y2>"," <z2>"};

             if (args.length > 7){
                 for (int i = 1; i < 8; i++) {
                     if (args[i].isEmpty()) {
                         ThrowError = true;
                         errorMSG = errorMSG + ErrorArray[i - 1];
                     } else if (i > 1) {
                         if (!isNumeric(args[i])) {
                             ThrowError = true;
                             errorMSG = errorMSG + ErrorArray[i - 1];
                         }
                     }
                 }
             }
             else {
                 for (int i = 1; i < args.length ; i++) {
                     if (args[i].isEmpty()) {
                         errorMSG = errorMSG + ErrorArray[i - 1];
                     } else if (i > 1) {
                         if (!isNumeric(args[i])) {
                             errorMSG = errorMSG + ErrorArray[i - 1];
                         }
                     }
                 }
                 ThrowError = true;
                 for( int i = args.length; i < 8; i++){
                     errorMSG = errorMSG + ErrorArray[i-1];
                 }
             }

             if (ThrowError){
                 sender.sendMessage(ChatColor.RED+errorMSG);
             }
             // if error checking valid start data writing
             else{
                // step 1) get json data and see if name exists
                 try {
                     String FullJson = data.readVaultData();
                     List<String> keyInfo = JsonPath.read(FullJson, "$..Name");

                     boolean isDuplicate = false;
                     int vaultLocation = keyInfo.size();
                     for (int i = 0; i < keyInfo.size(); i++) {
                         if (keyInfo.get(i).equals(args[1])){
                             isDuplicate = true;
                             vaultLocation = i;
                         }
                     }
                     // step 2) add applicable data
                     String mainDir = "$['Vault"+vaultLocation+"']";

                     if (!isDuplicate){
                         FullJson = FullJson.replaceAll("}$", "");
                         FullJson = FullJson + ",\"Vault"+vaultLocation+"\":{\"VaultP1\":{\"x\":0,\"y\":0,\"z\":0},\"traderP\":{\"x\":0,\"y\":0,\"z\":0},\"VaultP2\":{\"x\":0,\"y\":0,\"z\":0},\"VaultTrades\":{\"block 1\":30,\"block 2\":60,\"block 3\":120},\"Name\":\"None\"}}";
                     }
                     String newJson = JsonPath.parse(FullJson).set(mainDir+"['VaultP1']['x']", Integer.parseInt(args[2])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['VaultP1']['y']", Integer.parseInt(args[3])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['VaultP1']['z']", Integer.parseInt(args[4])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['VaultP2']['x']", Integer.parseInt(args[5])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['VaultP2']['y']", Integer.parseInt(args[6])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['VaultP2']['z']", Integer.parseInt(args[7])).jsonString();

                     if (isDuplicate){
                         if (data.writeVaultData(data.REWRITE,newJson) == Errors.NOERROR){
                             sender.sendMessage(ChatColor.GREEN+args[1]+" Positions updated!");
                             newJson = null;
                             errorMSG = null;
                             ThrowError = null;
                             ErrorArray = null;
                             FullJson = null;
                             keyInfo = null;

                         }
                     }
                     else {
                         newJson = JsonPath.parse(newJson).set(mainDir+"['Name']", args[1]).jsonString();
                         if (data.writeVaultData(data.REWRITE,newJson) == Errors.NOERROR){
                             sender.sendMessage(ChatColor.GREEN+args[1]+" Vault created! Trader needed");
                             newJson = null;
                             errorMSG = null;
                             ThrowError = null;
                             ErrorArray = null;
                             FullJson = null;
                             keyInfo = null;

                         }
                     }



                 } catch (IOException e) {
                     e.printStackTrace();
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }



             }


         }
         else if (args[0].toLowerCase().equals("getvault")){
             try {
                 String FullJson = data.readVaultData();
                 List<String> keyInfo = JsonPath.read(FullJson, "$..Name");

                 Integer vaultLocation = null;
                 for (int i = 0; i < keyInfo.size(); i++) {
                     if (keyInfo.get(i).equals(args[1])){
                         vaultLocation = i;
                     }
                 }
                 if (vaultLocation != null){
                     String mainDir = "$['Vault"+vaultLocation+"']";
                     List<String> values = JsonPath.read(FullJson, mainDir+"['VaultP1'][*]");
                     List<String> values2 = JsonPath.read(FullJson, mainDir+"['VaultP2'][*]");
                     sender.sendMessage(args[1]+"Positions are :"+ values.toString()+","+values2.toString());
                 }
                 else {
                     sender.sendMessage(ChatColor.RED+args[1]+" is not a valid vault");
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ParseException e) {
                 e.printStackTrace();
             }


         }

         else if (args[0].toLowerCase().equals("settrader")){
             // error checking (make sure all input is valid)
             String errorMSG ="The following values are missing or incorrect:";
             Boolean ThrowError = false;
             String[] ErrorArray ={" <name>"," <x>"," <y>"," <z>"};

             if (args.length > 4){
                 for (int i = 1; i < 5; i++) {
                     if (args[i].isEmpty()) {
                         ThrowError = true;
                         errorMSG = errorMSG + ErrorArray[i - 1];
                     } else if (i > 1) {
                         if (!isNumeric(args[i])) {
                             ThrowError = true;
                             errorMSG = errorMSG + ErrorArray[i - 1];
                         }
                     }
                 }
             }
             else {
                 for (int i = 1; i < args.length ; i++) {
                     if (args[i].isEmpty()) {
                         errorMSG = errorMSG + ErrorArray[i - 1];
                     } else if (i > 1) {
                         if (!isNumeric(args[i])) {
                             errorMSG = errorMSG + ErrorArray[i - 1];
                         }
                     }
                 }
                 ThrowError = true;
                 for( int i = args.length; i < 5; i++){
                     errorMSG = errorMSG + ErrorArray[i-1];
                 }
             }

             if (ThrowError){
                 sender.sendMessage(ChatColor.RED+errorMSG);
             }
             // if error checking valid start data writing
             else{
                 // step 1) get json data and see if name exists
                 try {
                     String FullJson = data.readVaultData();
                     List<String> keyInfo = JsonPath.read(FullJson, "$..Name");

                     boolean isDuplicate = false;
                     int vaultLocation = keyInfo.size();
                     for (int i = 0; i < keyInfo.size(); i++) {
                         if (keyInfo.get(i).equals(args[1])){
                             isDuplicate = true;
                             vaultLocation = i;
                         }
                     }
                     // step 2) add applicable data
                     String mainDir = "$['Vault"+vaultLocation+"']";

                     if (!isDuplicate){
                         FullJson = FullJson.replaceAll("}$", "");
                         FullJson = FullJson + ",\"Vault"+vaultLocation+"\":{\"VaultP1\":{\"x\":0,\"y\":0,\"z\":0},\"traderP\":{\"x\":0,\"y\":0,\"z\":0},\"VaultP2\":{\"x\":0,\"y\":0,\"z\":0},\"VaultTrades\":{\"block 1\":30,\"block 2\":60,\"block 3\":120},\"Name\":\"None\"}}";
                     }
                     String newJson = JsonPath.parse(FullJson).set(mainDir+"['traderP']['x']", Integer.parseInt(args[2])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['traderP']['y']", Integer.parseInt(args[3])).jsonString();
                     newJson = JsonPath.parse(newJson).set(mainDir+"['traderP']['z']", Integer.parseInt(args[4])).jsonString();

                     if (isDuplicate){
                         if (data.writeVaultData(data.REWRITE,newJson) == Errors.NOERROR){
                             sender.sendMessage(ChatColor.GREEN+args[1]+" Trader updated!");
                             newJson = null;
                             errorMSG = null;
                             ThrowError = null;
                             ErrorArray = null;
                             FullJson = null;
                             keyInfo = null;

                         }
                     }
                     else {
                         newJson = JsonPath.parse(newJson).set(mainDir+"['Name']", args[1]).jsonString();
                         if (data.writeVaultData(data.REWRITE,newJson) == Errors.NOERROR){
                             sender.sendMessage(ChatColor.GREEN+args[1]+" Vault created! Vault positions needed");
                             newJson = null;
                             errorMSG = null;
                             ThrowError = null;
                             ErrorArray = null;
                             FullJson = null;
                             keyInfo = null;

                         }
                     }



                 } catch (IOException e) {
                     e.printStackTrace();
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }



             }
         }
         else if (args[0].toLowerCase().equals("gettrader")){
             try {
                 String FullJson = data.readVaultData();
                 List<String> keyInfo = JsonPath.read(FullJson, "$..Name");

                 Integer vaultLocation = null;
                 for (int i = 0; i < keyInfo.size(); i++) {
                     if (keyInfo.get(i).equals(args[1])){
                         vaultLocation = i;
                     }
                 }
                 if (vaultLocation != null){
                     String mainDir = "$['Vault"+vaultLocation+"']";
                     List<String> values = JsonPath.read(FullJson, mainDir+"['traderP'][*]");
                     sender.sendMessage(args[1]+"'s Trader is located at :"+ values.toString());
                 }
                 else {
                     sender.sendMessage(ChatColor.RED+args[1]+" is not a valid vault");
                 }

             } catch (IOException e) {
                 e.printStackTrace();
             } catch (ParseException e) {
                 e.printStackTrace();
             }


         }




            /// EconVaults
            //      setVault <name> <x><y><z> <x><y><z>
            //      setTrader <x><y><z>


        return false;
    }
}
