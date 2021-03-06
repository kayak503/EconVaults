package com.projectpalm.econvaults.econvaults;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.Events.ClickEvent;
import com.projectpalm.econvaults.econvaults.Events.onPlayerInteractEntity;
import com.projectpalm.econvaults.econvaults.NMS_versions.VersionManager;
import com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2.Manager_v1_16_R2;
import com.projectpalm.econvaults.econvaults.Vault.VaultHook;
import com.projectpalm.econvaults.econvaults.Vault.VaultImplementation;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.plugin.java.JavaPlugin;
import java.util.Objects;


public final class EconVaults extends JavaPlugin {
    // Vault hook variables
    public static EconVaults getInstance; // For Using non Static Methods and variables in EconVaults class
    public VaultImplementation economyImplementer;
    private VaultHook vaultHook;
    // NMS Version Manager
    public VersionManager tab;
    public String sVersion;
    public static World OverWorld;


    @Override
    public void onEnable() {
        instanceClasses(); // Initialization of non static Methods and Variables
        vaultHook.hook(); // Vault Hooking
        Startup.StartUpEconVaults();


        //this.saveDefaultConfig();


        // Command Class links
        Objects.requireNonNull(getCommand("EconVaults")).setExecutor( new com.projectpalm.econvaults.econvaults.commands.EconVaults());
        Objects.requireNonNull(getCommand("Money")).setExecutor( new com.projectpalm.econvaults.econvaults.commands.money());

        //Event Class links
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);
        getServer().getPluginManager().registerEvents(new onPlayerInteractEntity(), this);
        //getServer().getPluginManager().registerEvents(new PlayerJoinEvent(), this);




    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        vaultHook.unhook();
        VaultData AllVaults[] = Data.GetAllVaults();
        for (int i = 0; i < AllVaults.length; i++){
            tab.killTeller(AllVaults[i].GetUuid());
        }
    }

    private void instanceClasses() {

        getInstance = this;
        // Vault hook Initialization
        economyImplementer = new VaultImplementation();
        vaultHook = new VaultHook();
        // Version Manager
        sVersion ="N/A";
        try{ sVersion = Bukkit.getServer().getClass().getPackage().getName().split("\\.")[3]; }
        catch (ArrayIndexOutOfBoundsException e){}
        switch (sVersion){
            case "v1_16_R2":
                tab = new Manager_v1_16_R2();
                break;
            case "N/A":
                getLogger().severe("[EconVaults] Server Version Error, Critical  ");
                ToolBox.TerminatePlugin();
        }
        if(tab == null){
            getLogger().severe("[EconVaults] Server Version Not supported , Plugin Disabling...");
            ToolBox.TerminatePlugin();
        }
        OverWorld = this.getServer().getWorld("World");
        if (OverWorld == null){
            System.out.println(ToolBox.ErrorBase +"World null");
        }
        else{
            System.out.println(ToolBox.Base +"OverWorld Captured");
        }
    }



}
