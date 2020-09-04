package com.projectpalm.econvaults.econvaults;

import com.projectpalm.econvaults.econvaults.commands.TriggerCommand;
import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Objects;

public final class EconVaults extends JavaPlugin {

    @Override
    public void onEnable() {
        // Plugin startup logic
        int startStatus = data.startPlugin();
        if (startStatus != Errors.NOERROR){
            System.out.println(ChatColor.RED +"Error "+startStatus+" Please review documentation for more details");
        }
        Objects.requireNonNull(getCommand("EconVaults")).setExecutor( new TriggerCommand());

    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

}
