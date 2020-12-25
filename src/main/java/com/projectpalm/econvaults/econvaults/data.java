//https://github.com/json-path/JsonPath
package com.projectpalm.econvaults.econvaults;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class data {


    private static final String FOLDER = "plugins\\EconVaults";
    private static final String DIR = System.getProperty("user.dir");
    public static final String VAULTDATA = "VaultData.json";
    public static final String PLAYERDATA = "playerData.json";

    // PRIVET METHODS

    private static List<Integer> CheckDirectoryExist(){

        List<Integer> Error = new ArrayList<Integer>();

        File Folder = new File(DIR+"\\"+ FOLDER);
        if (!Folder.isDirectory()){
            Error.add(Errors.DERECTORYNOTFOUND);
        }
        File VaultData = new File(DIR +"\\"+ FOLDER +"\\"+ VAULTDATA);
        if (!VaultData.exists()){
            Error.add(Errors.VAUTLTFILENOTFOUND);
        }
        File PlayerData = new File(DIR +"\\"+ FOLDER +"\\"+ PLAYERDATA);
        if (!PlayerData.exists()){
            Error.add(Errors.PLAYERFILENOTFOUND);
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
                VaultData.createNewFile();
                if (WriteData(VAULTDATA, "{}") != Errors.NOERROR) {
                    return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }

        File PlayerData = new File(DIR+"\\"+FOLDER+"\\"+PLAYERDATA);
        if (!PlayerData.isFile()) {
            try {
                PlayerData.createNewFile();
                if (WriteData(PLAYERDATA, "{}") != Errors.NOERROR) {
                    return false;
                }

            } catch (IOException e) {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
    private static int createPlayerData(String PlayerName) {
        String FullJson = ReadData(PLAYERDATA); //read data
        if (FullJson != null) {

            FullJson = FullJson.replaceAll("}$", "");
            if (FullJson.length() < 2){
                FullJson = FullJson + "\""+ PlayerName+"\":{\"Money\":0}}";
            }
            else {
                FullJson = FullJson + ",\""+ PlayerName+"\":{\"Money\":0}}";
            }

            // add new player
           int error =  WriteData(PLAYERDATA, FullJson); //try to write new player to file
           if (error!= Errors.NOERROR){ // write failed
               System.out.println("\uD83D\uDCD9 [EconVaults] playerData.json write failed, Error(s) :"+error+" Raised \uD83D\uDCD9 ");
               return Errors.DATWRITEFAILD;
           }
           else{ // write success
               return Errors.NOERROR;
           }
        } else { // read data failed
            return Errors.DATAREQUESTFAILED;
        }
    }
    // PUBLIC METHODS

    public static String ReadData(String FileName) {
        String Dir = DIR+"\\"+ FOLDER +"\\"+ FileName;

        JSONParser jsonParser = new JSONParser();

        try {
            FileReader reader = new FileReader(Dir);
            Object jsonObject = jsonParser.parse(reader);
            return jsonObject.toString();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return null;
    }
    public static int WriteData(String FileName, String jsonString){
        String Dir = DIR+"\\"+ FOLDER +"\\"+ FileName;

        try {
            FileWriter file = new FileWriter(Dir);
            file.write(jsonString);
            file.flush();
            return Errors.NOERROR;

        } catch (IOException e) {
            e.printStackTrace();
            return Errors.IOException;
        }
    }
    public static int FileCheck() {

        List<Integer> ErrorList = CheckDirectoryExist();
        if (ErrorList.isEmpty()){
            return Errors.NOERROR;
        }
        else {
            System.out.println("\uD83D\uDCD9 [EconVaults] file(s) were corrupted, deleted or never existed Error(s) :"+ErrorList.toString()+" Raised \uD83D\uDCD9 ");
            System.out.println("[EconVaults] Attempting to recreate file(s)");
            if (createDirectory()){
                System.out.println("\uD83D\uDCD7 [EconVaults] file(s) remade, plugin resuming \uD83D\uDCD7");
                return Errors.NOERROR;
            }
            else {
                System.out.println("\uD83D\uDCD5 [EconVaults] Failed to recreate file(s), plugin can not precede \uD83D\uDCD5");
                System.exit(0);
                return Errors.FILECREATIONFAILED;
            }
        }
    }

    public static int ReadPlayerMoney(String PlayerName){
        String RawData = ReadData(PLAYERDATA);
        if (RawData!= null){

            try {
                Integer playerMoney = JsonPath.read(RawData, "$."+PlayerName+".Money");
                return playerMoney;
            }
            catch (Exception e){
                System.out.println("\\u001B[33m [EconVaults] "+ e+ " \\u001B[37m");
               int error = createPlayerData(PlayerName);
               if (error != Errors.NOERROR){
                   System.out.println("\uD83D\uDCD9 [EconVaults] playerData.json player creation failed, Error(s) :"+error+" Raised \uD83D\uDCD9 ");
                   return Errors.DATWRITEFAILD*-1;
               }
                return 0;
            }
            //return Integer.parseInt(playerMoney.toString());


        }
        else {
            return Errors.DATAREQUESTFAILED*-1;
        }
    }
    public static int WritePlayerMoney(String PlayerName, int Amount) {
        String RawData = ReadData(PLAYERDATA);
        if (RawData != null) {
            try {
                Integer playerMoney = JsonPath.read(RawData, "$." + PlayerName + ".Money");
            } catch (Exception e) {
                System.out.println("\\u001B[33m [EconVaults] " + e + " \\u001B[37m");
                int error = createPlayerData(PlayerName);
                if (error != Errors.NOERROR) {
                    System.out.println("\uD83D\uDCD9 [EconVaults] playerData.json player creation failed, Error(s) :" + error + " Raised \uD83D\uDCD9 ");
                    return Errors.DATWRITEFAILD;
                }
                String newJson = JsonPath.parse(RawData).set("$." + PlayerName + ".Money", Amount).jsonString();
                error = WriteData(PLAYERDATA, newJson);
                if (error != Errors.NOERROR) {
                    System.out.println("\uD83D\uDCD9 [EconVaults] playerData.json write failed, Error(s) :" + error + " Raised \uD83D\uDCD9 ");
                    return Errors.DATWRITEFAILD;
                }
                return Errors.NOERROR;
            }
            return 0;
        }
        return Errors.DATAREQUESTFAILED;
    }
    public static int AdjustPlayerMoney(String PlayerName, int Adjust){
        int currentValue =  ReadPlayerMoney(PlayerName);
        if (currentValue > 0){
            int Amount = currentValue + Adjust;
           int error =  WritePlayerMoney(PlayerName,Amount);
            if (error != Errors.NOERROR) {
                System.out.println("\uD83D\uDCD9 [EconVaults] playerData.json write failed, Error(s) :" + error + " Raised \uD83D\uDCD9 ");
                return Errors.DATWRITEFAILD;
            }
            return Errors.NOERROR;
        }
        return Errors.DATAREQUESTFAILED;
    }





}
