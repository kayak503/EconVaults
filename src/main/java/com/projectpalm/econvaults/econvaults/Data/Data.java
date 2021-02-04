// Uses  https://github.com/json-path/JsonPath, https://github.com/google/gson

package com.projectpalm.econvaults.econvaults.Data;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jayway.jsonpath.JsonPath;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.ToolBox;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class Data {
    // Data Class Will handle all of json related parsing outside methods should only deal with object classes

    // Need to add iterator functionality to playerData class and VaultData class, replace json fetch.ToString with type classes

    private static final String FOLDER = "plugins\\EconVaults";
    private static final String DIR = System.getProperty("user.dir");
    public static final String VAULTDATA = "VaultData.json";
    public static final String PLAYERDATA = "playerData.json";
    static final Gson gsonCreate = new GsonBuilder().create();
    //private static final Object PlayerLock = new Object(); Multi thread cap
    //private static final Object VaultLock = new Object();

    // PRIVET METHODS
    private static List<Integer> CheckDirectoryExist(){

        List<Integer> Error = new ArrayList<>();

        File Folder = new File(DIR+"\\"+ FOLDER);
        if (!Folder.isDirectory()){
            Error.add(ToolBox.DERECTORYNOTFOUND);
        }
        File VaultData = new File(DIR +"\\"+ FOLDER +"\\"+ VAULTDATA);
        if (!VaultData.exists()){
            Error.add(ToolBox.VAUTLTFILENOTFOUND);
        }
        File PlayerData = new File(DIR +"\\"+ FOLDER +"\\"+ PLAYERDATA);
        if (!PlayerData.exists()){
            Error.add(ToolBox.PLAYERFILENOTFOUND);
        }
        return Error;
    }
    private static boolean createDirectory(){

        File dir = new File(DIR+"\\"+FOLDER);
        if (!dir.isDirectory()) {
            //Creating the directory
            boolean bool = dir.mkdir();
            if (!bool) {
                return false;
            }
        }

        File VaultData = new File(DIR+"\\"+FOLDER+"\\"+VAULTDATA);
        if (!VaultData.isFile()) {
            try {
                if (VaultData.createNewFile()){
                    if (WriteData(VAULTDATA, "{}") != ToolBox.NOERROR) {
                        return false;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        File PlayerData = new File(DIR+"\\"+FOLDER+"\\"+PLAYERDATA);
        if (!PlayerData.isFile()) {
            try {
                if (PlayerData.createNewFile()) {
                    if (WriteData(PLAYERDATA, "{ \"00000000-0000-0000-0000-000000000000\":{\n" +
                            "  \"Name\": \"Net Money\",\n" +
                            "  \"Money\": 0,\n" +
                            "  \"uuid\": \"00000000-0000-0000-0000-000000000000\"\n" +
                            " }}") != ToolBox.NOERROR) {
                        return false;
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    private static String ReadData(String FileName) {
        String Dir = DIR+"\\"+ FOLDER +"\\"+ FileName;

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(Dir);
            Object jsonObject = jsonParser.parse(reader);
            return jsonObject.toString();

        } catch (ParseException | IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    private static int WriteData(String FileName, String jsonString){
        String Dir = DIR+"\\"+ FOLDER +"\\"+ FileName;

        try {
            FileWriter file = new FileWriter(Dir);
            file.write(jsonString);
            file.flush();
            return ToolBox.NOERROR;

        } catch (IOException e) {
            e.printStackTrace();
            return ToolBox.IOException;
        }
    }

    // PUBLIC METHODS
    public static void RunDiagnostics() {

        List<Integer> ErrorList = CheckDirectoryExist();
        if (ErrorList.isEmpty()){

        }
        else {
            System.out.println(ToolBox.CautionBase + "file(s) were corrupted, deleted or never existed Error(s) :"+ErrorList.toString()+" Raised");
            System.out.println(ToolBox.CautionBase +" Attempting to recreate file(s)");
            if (createDirectory()){
                System.out.println( ToolBox.CautionBase +"file(s) remade, plugin resuming ");

            }
            else {
                System.out.println(ToolBox.ErrorBase + "Failed to recreate file(s), plugin can not precede");
                ToolBox.TerminatePlugin();

            }
        }
    }

    // Vault Data

        // ReadData
    public static VaultData GetVaultByName(String name, Player player){
        String playerName = player.getName();
        String VaultDataFileRawJson;
        String filter = "$.[*].[?( @.Name == \""+name+"\")]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }

                VaultData Vault = gsonCreate.fromJson(MatchingVault, VaultData.class); // turn into vault class
                String Owners[] = Vault.GetOwners(); // get list of owners
                for (String Owner:Owners){ // loop through list of owners and return vault if player matches list
                    if( Owner.equalsIgnoreCase(playerName)){
                        return Vault;
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        return null;
    }
    public static VaultData GetVaultByName(String name, String playerName ){
        String VaultDataFileRawJson;
        String filter = "$.[*].[?( @.Name == \""+name+"\")]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }

                VaultData Vault = gsonCreate.fromJson(MatchingVault, VaultData.class); // turn into vault class
                String Owners[] = Vault.GetOwners(); // get list of owners
                for (String Owner:Owners){ // loop through list of owners and return vault if player matches list
                    if( Owner.equalsIgnoreCase(playerName)){
                        return Vault;
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        return null;
    }

    public static VaultData GetVaultByUuid(UUID uuid){
        String StringUUID = uuid.toString();
        //String VaultDataFileRawJson;
        String filter = "$.[*].[?( @.uuid == \""+StringUUID+"\")]";
        String VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultRaw = MatchingVaultRaw.substring(1,MatchingVaultRaw.length()-1); // remove first and last [ ]
            return gsonCreate.fromJson(MatchingVaultRaw, VaultData.class);

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        return null;
    }
    public static VaultData[] GetAllVaults(){
        List<VaultData> Vaults = new ArrayList<>();
        String VaultDataFileRawJson;
        String filter = "$.[*]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }
                Vaults.add(gsonCreate.fromJson(MatchingVault, VaultData.class)); // turn into vault class
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        if (Vaults.isEmpty()){
            return null;
        }

        VaultData returnList[] = new VaultData[Vaults.size()];
        returnList = Vaults.toArray(returnList);
        if(returnList[0] == null){
            return null;
        }
        return returnList;

    }
    public static VaultData[] GetAllPlayerVaults(String playerName){
        List<VaultData> Vaults = new ArrayList<>();
        String VaultDataFileRawJson;
        String filter = "$.[*]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }

                VaultData Vault = gsonCreate.fromJson(MatchingVault, VaultData.class); // turn into vault class
                String Owners[] = Vault.GetOwners(); // get list of owners
                for (String Owner:Owners){ // loop through list of owners and return vault if player matches list
                    if( Owner.equalsIgnoreCase(playerName)){
                       Vaults.add(Vault);
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        if (Vaults.isEmpty()){
            return null;
        }
        VaultData returnList[] = new VaultData[Vaults.size()];
        returnList = Vaults.toArray(returnList);
        if(returnList[0] == null){
            return null;
        }
        return returnList;
    }
    public static VaultData[] GetAllPlayerVaults(Player player ){
        String playerName = player.getName();
        List<VaultData> Vaults = new ArrayList<>();
        String VaultDataFileRawJson;
        String filter = "$.[*]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }

                VaultData Vault = gsonCreate.fromJson(MatchingVault, VaultData.class); // turn into vault class
                String Owners[] = Vault.GetOwners(); // get list of owners
                for (String Owner:Owners){ // loop through list of owners and return vault if player matches list
                    if( Owner.equalsIgnoreCase(playerName)){
                        Vaults.add(Vault);
                    }
                }
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        if (Vaults.isEmpty()){
            return null;
        }
        VaultData returnList[] = new VaultData[Vaults.size()];
        returnList = Vaults.toArray(returnList);
        if(returnList[0] == null){
            return null;
        }
        return returnList;

    }

    public static VaultData[] GetAllTeamVaults(String team){
        List<VaultData> Vaults = new ArrayList<>();
        String VaultDataFileRawJson;
        String filter = "$.[*].[?( @.Team == \""+team+"\")]";
        VaultDataFileRawJson = ReadData(VAULTDATA);

        try {
            String MatchingVaultsRaw = (JsonPath.read(VaultDataFileRawJson, filter)).toString(); // get all vaults with matching names from method String name
            MatchingVaultsRaw = MatchingVaultsRaw.substring(1,MatchingVaultsRaw.length()-1); // remove first and last [ ]
            String MatchingVaults[] = MatchingVaultsRaw.split("},"); // separate vaults into list
            for (String MatchingVault:MatchingVaults){  // loop through vaults
                if( MatchingVault.endsWith("]")){ // if vault lost } through split add it back
                    MatchingVault += "}";
                }
                Vaults.add(gsonCreate.fromJson(MatchingVault, VaultData.class)); // turn into vault class
            }

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetVaultByName() :" + e.toString());
        }
        if (Vaults.isEmpty()){
            System.out.println("Got here");
            return null;
        }
        VaultData returnList[] = new VaultData[Vaults.size()];
        returnList = Vaults.toArray(returnList);
        if(returnList[0] == null){
            return null;
        }
        return returnList;
    }

        // WriteData
    public static void AddNewVault(VaultData vault){
        vault.GenUuid();
        if (GetVaultByUuid(vault.GetUuid()) == null) {
            String rawJson = "\"" + vault.GetUuid().toString() + "\":" + gsonCreate.toJson(vault) + " \n}";

            String VaultDataFileRawJson = ReadData(VAULTDATA).replaceAll("}$", "");
            ;
            if (VaultDataFileRawJson.length() > 10) {
                rawJson = "," + rawJson;
            }
            VaultDataFileRawJson += rawJson;
            if (WriteData(VAULTDATA, VaultDataFileRawJson) == ToolBox.IOException) {
                System.out.println(ToolBox.ErrorBase + " Fatal Error " + ToolBox.IOException + " Please review documentation for more details");
                ToolBox.TerminatePlugin();
            }
        }else {
            UpdateVault(vault);
        }
    }
    public static void UpdateVault(VaultData vault){
        RemoveVault(vault);
        AddNewVault(vault);
    }
    public static void RemoveVault(VaultData vault){
        UUID uuid = vault.GetUuid();
        VaultData Vaults[] = GetAllVaults();
        if (Vaults == null){
            System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [1/3]" );
            Vaults = GetAllVaults();
            if (Vaults == null){
                System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [2/3]" );
                Vaults = GetAllVaults();
                if (Vaults == null){
                    System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [3/3]" );
                    System.out.println(ToolBox.ErrorBase + "Fatal plugin Error, Terminating plugin" );
                    ToolBox.TerminatePlugin();
                }
            }
        }

        String newJson = "{";
        for (int i = 0; i < Vaults.length; i++){
            VaultData Vault = Vaults[i];
            if(Vault.GetUuid().equals(uuid)){
                continue;
            }
            else{
                if (newJson.length() > 10) {
                    newJson += ",\"" + Vault.GetUuid().toString() + "\":" + gsonCreate.toJson(Vault) + " \n";
                }
                else {
                    newJson += "\"" + Vault.GetUuid().toString() + "\":" + gsonCreate.toJson(Vault) + " \n";
                }
            }
        }
        newJson += "}";
        if (WriteData(VAULTDATA, newJson) == ToolBox.IOException) {
            System.out.println(ToolBox.ErrorBase + " Fatal Error " + ToolBox.IOException + " Please review documentation for more details");
            ToolBox.TerminatePlugin();
        }
    }
    public static void RemoveVault(UUID uuid){
        VaultData Vaults[] = GetAllVaults();
        if (Vaults == null){
            System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [1/3]" );
            Vaults = GetAllVaults();
            if (Vaults == null){
                System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [2/3]" );
                Vaults = GetAllVaults();
                if (Vaults == null){
                    System.out.println(ToolBox.ErrorBase + "RemoveVault() Data Collection Failed, Attempt [3/3]" );
                    System.out.println(ToolBox.ErrorBase + "Fatal plugin Error, Terminating plugin" );
                    ToolBox.TerminatePlugin();
                }
            }
        }

        String newJson = "{";
        for (int i = 0; i < Vaults.length; i++){
            VaultData Vault = Vaults[i];
            if(Vault.GetUuid().equals(uuid)){
                continue;
            }
            else{
                if (newJson.length() > 10) {
                    newJson += ",\"" + Vault.GetUuid().toString() + "\":" + gsonCreate.toJson(Vault) + " \n";
                }
                else {
                    newJson += "\"" + Vault.GetUuid().toString() + "\":" + gsonCreate.toJson(Vault) + " \n";
                }
            }
        }
        newJson += "}";
        if (WriteData(VAULTDATA, newJson) == ToolBox.IOException) {
            System.out.println(ToolBox.ErrorBase + " Fatal Error " + ToolBox.IOException + " Please review documentation for more details");
            ToolBox.TerminatePlugin();
        }
    }

    // Player Data

        // ReadData
    public static PlayerData GetPlayerData(String name) {
        String PlayerDataFileRawJson;
        String filter = "$..[?( @.Name == \""+name+"\" )]";
        PlayerDataFileRawJson = ReadData(PLAYERDATA);

        try {
            String JsonString = (JsonPath.read(PlayerDataFileRawJson, filter)).toString().replace("[", "").replace("]","");
             return gsonCreate.fromJson(JsonString, PlayerData.class);
        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetPlayerData() :" + e.toString());
        }
        return null;
    }
    public static PlayerData GetPlayerData(UUID uuid) {
        String PlayerDataFileRawJson = ReadData(PLAYERDATA);
        String filter = "$..[?( @.uuid == \""+uuid+"\" )]";
        try {
            String JsonString = (JsonPath.read(PlayerDataFileRawJson, filter)).toString().replace("[", "").replace("]","");
            return gsonCreate.fromJson(JsonString, PlayerData.class);
        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.GetPlayerData() :" + e.toString());
        }
        return null;
    }
    public static int GetNetMoney (){
        PlayerData NetMoney = GetPlayerData( "Net Money");
        return NetMoney.GetMoney();
    }

        // WriteData
    public static void AddNewPlayerData(PlayerData player){
        if (player.GetMoney() == null){
            player.SetMoney(0);
        }
        String rawJson = ",\""+ player.GetUuid().toString()+ "\":" + gsonCreate.toJson(player) + " \n}";
        String PlayerDataFileRawJson = ReadData(PLAYERDATA).replaceAll("}$", "");;
        PlayerDataFileRawJson += rawJson;
        if (WriteData(PLAYERDATA, PlayerDataFileRawJson) == ToolBox.IOException){
            System.out.println(ToolBox.ErrorBase +" Fatal Error "+ ToolBox.IOException+" Please review documentation for more details");
            Bukkit.getPluginManager().disablePlugin(EconVaults.getInstance);
        }
    }
    public static void UpdatePlayerData(PlayerData player){
        String PlayerDataFileRawJson = ReadData(PLAYERDATA);

        String newJson = JsonPath.parse(PlayerDataFileRawJson).set("$[\""+player.GetUuid()+"\"]['Money']", player.GetMoney()).jsonString();
        newJson = JsonPath.parse(newJson).set("$[\""+player.GetUuid()+"\"]['Name']", player.GetName()).jsonString();

        if (WriteData(PLAYERDATA, newJson) == ToolBox.IOException){
            System.out.println(ToolBox.ErrorBase +" Fatal Error "+ ToolBox.IOException+" Please review documentation for more details");
            Bukkit.getPluginManager().disablePlugin(EconVaults.getInstance);
        }


    }
    public static void UpdateNetMoney( int NewNetMoney){
        PlayerData NetMoney = GetPlayerData( "Net Money");
        NetMoney.SetMoney(NewNetMoney);
        UpdatePlayerData(NetMoney);

    }
    public static void UpdateNetMoneyCount(){
        int CurrentMoneyCount = GetNetMoney();

        String PlayerDataFileRawJson;
        String filter = "$..Money";
        PlayerDataFileRawJson = ReadData(PLAYERDATA);
        try {
            List<Integer> MoneyList = JsonPath.read(PlayerDataFileRawJson, filter);
            int totalMoney = MoneyList.stream().mapToInt(x -> x).sum() - CurrentMoneyCount;
            UpdateNetMoney(totalMoney);

        }
        catch (Exception e){
            System.out.println(ToolBox.CautionBase + "Data.UpdateNetMoneyCount() :" + e.toString());
        }

    }

}
