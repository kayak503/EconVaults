package com.projectpalm.econvaults.econvaults;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

public class ToolBox {
    public static final String ErrorBase = "[EconVaults] " + ChatColor.RED;
    public static final String CautionBase = "[EconVaults] " + ChatColor.YELLOW;
    public static final String Base = "[EconVaults] " ;



    public static final Integer NOERROR = 0;
    public static final Integer DERECTORYNOTFOUND = 1;
    public static final Integer VAUTLTFILENOTFOUND = 2;
    public static final Integer PLAYERFILENOTFOUND = 3;
    public static final Integer FILECREATIONFAILED = 4;
    public static final Integer IOException = 5;
    public static final Integer DATAREQUESTFAILED = 6;
    public static final Integer DATWRITEFAILD = 7;

    public static void TerminatePlugin()
    {
        Bukkit.getPluginManager().disablePlugin(EconVaults.getInstance);
    }
    public static boolean IsNumeric(String string){
        try {
            Double num = Double.parseDouble(string);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
