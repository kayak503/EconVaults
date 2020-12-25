package com.projectpalm.econvaults.econvaults;

import com.projectpalm.econvaults.econvaults.Events.ClickEvent;
import com.projectpalm.econvaults.econvaults.Vault.VaultHook;
import com.projectpalm.econvaults.econvaults.Vault.VaultImplementation;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;


public final class EconVaults extends JavaPlugin {
    // Vault hook variables
    public static EconVaults getInstance;
    public VaultImplementation economyImplementer;
    private VaultHook vaultHook;
    public HashMap<UUID,Double> playerBank = new HashMap<UUID,Double>();



    @Override
    public void onEnable() {
        // Plugin startup logic
        // Vault hook Initialization and Hook
        instanceClasses();
        vaultHook.hook();


        int startStatus = data.FileCheck();
        if (startStatus != Errors.NOERROR){
            System.out.println(ChatColor.RED +"Error "+startStatus+" Please review documentation for more details");
        }
        // Command Class links
        Objects.requireNonNull(getCommand("EconVaults")).setExecutor( new com.projectpalm.econvaults.econvaults.commands.EconVaults());
        Objects.requireNonNull(getCommand("Money")).setExecutor( new com.projectpalm.econvaults.econvaults.commands.money());

        //Event Class links
        getServer().getPluginManager().registerEvents(new ClickEvent(), this);

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
        vaultHook.unhook();
    }


    private void instanceClasses() {
        // Vault hook Initialization
        getInstance = this;
        economyImplementer = new VaultImplementation();
        vaultHook = new VaultHook();
    }


}
