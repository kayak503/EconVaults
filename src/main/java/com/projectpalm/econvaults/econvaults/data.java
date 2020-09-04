//https://github.com/json-path/JsonPath
package com.projectpalm.econvaults.econvaults;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class data {
    public static final Integer REWRITE = 0;
    public static final Integer REPLACE = 1;


    private static final String PLUGINFOLDERNAME = "plugins\\EconVaults"; // name of plugin folder
    private static final String PLUGINDIR = System.getProperty("user.dir"); //gets path of plugin
    private static final String PLUGINDATAFILENAME = "data"; //name of file were data is stored






    private static int fileDirectoryExist(){
        File pluginFolder = new File(PLUGINDIR+"\\"+ PLUGINFOLDERNAME);
        if (pluginFolder.isDirectory()){ // checks if econVaults is a folder
            File datafile = new File(PLUGINDIR+"\\"+ PLUGINFOLDERNAME+"\\"+PLUGINDATAFILENAME+".json");
            if (datafile.exists() && datafile.isFile()){
                return Errors.NOERROR;
            }
            return Errors.FILENOTFOUND;
        }
        return Errors.DERECTORYNOTFOUND;
    }

    private static boolean createDirectory(){

        File dir = new File(PLUGINDIR+"\\"+PLUGINFOLDERNAME);
        if (!dir.isDirectory()) {
            //Creating the directory
            boolean bool = dir.mkdir();
            if (!bool) {
                return false;
            }
        }

        File myFile = new File(PLUGINDIR+"\\"+PLUGINFOLDERNAME+"\\"+PLUGINDATAFILENAME+".json");
        try {
            myFile.createNewFile();
            if (writeVaultData(REWRITE, "{}") == Errors.NOERROR){
                return true;}
            return false;

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }

    }

    public static int startPlugin() {
        int fileDirectoryCodex = fileDirectoryExist();
        if (fileDirectoryCodex == Errors.DERECTORYNOTFOUND) {
            if (!createDirectory()) {
                System.out.println("[EconVaults] Unable To create plugin folder for EconVaults");
                return Errors.FOLDERCREATIONFAILD;
            }
        }
        else if (fileDirectoryCodex == Errors.FILENOTFOUND) {
            System.out.println("[EconVaults] "+PLUGINDATAFILENAME + ".json file was corrupted or deleted... attempting to remake " + PLUGINDATAFILENAME + " file");
            if (!createDirectory()) {
                System.out.println("[EconVaults] Unable To remake plugin file for EconVaults");
                return Errors.FOLDERCREATIONFAILD;
            }
            else {System.out.println("[EconVaults] successful remade plugin file for EconVaults");}
        }


        return Errors.NOERROR;
    }

    public static String readVaultData() throws IOException, ParseException {

       // List<String> authors = JsonPath.read("", "$.store.book[*].author");

        //step 1: load file into ram in usable format

        JSONParser jsonParser = new JSONParser(); // create parser object

        FileReader reader = new FileReader(PLUGINDIR+"\\"+PLUGINFOLDERNAME+"\\"+PLUGINDATAFILENAME+".json"); //Read JSON file
        Object jsonObject = jsonParser.parse(reader);  //parse JSON file

        return jsonObject.toString();
    }

    public static int writeVaultData(int mode, String jsonString){

        if (mode == REPLACE){

            return Errors.IOException;
        }
        if (mode == REWRITE){
            try (FileWriter file = new FileWriter(PLUGINDIR+"\\"+PLUGINFOLDERNAME+"\\"+PLUGINDATAFILENAME+".json")) {

                file.write(jsonString);
                file.flush();

            } catch (IOException e) {
                e.printStackTrace();
                return Errors.IOException;
            }
        }


        return Errors.NOERROR;
    }
}
