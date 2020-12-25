package com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2;

import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.EntityVillager;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

public class Mob_Trader extends EntityVillager {
    public Mob_Trader(Location loc){
        super(EntityTypes.VILLAGER, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.GOLD+ "" + ChatColor.BOLD + "Trader"));
        this.setCustomNameVisible(true);
        this.setHealth(999999999);
        this.setNoAI(true);
        this.setSilent(true);
    }

    public int spawnTrader(String uid, Player player){
        Mob_Trader mob = new Mob_Trader(player.getLocation());
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        world.addEntity(mob);

        return 0;
    }
}
