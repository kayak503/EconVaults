package com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2;

import net.minecraft.server.v1_16_R2.ChatComponentText;
import net.minecraft.server.v1_16_R2.EntityTypes;
import net.minecraft.server.v1_16_R2.EntityVillager;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Mob_Teller extends EntityVillager {
    public Mob_Teller(Location loc, UUID uuid){
        super(EntityTypes.VILLAGER, ((CraftWorld) loc.getWorld()).getHandle());

        this.setPosition(loc.getX(),loc.getY(),loc.getZ());
        this.setCustomName(new ChatComponentText(ChatColor.GOLD+ "" + ChatColor.BOLD + "Trader"));
        this.setCustomNameVisible(true);
        this.setInvulnerable(true);
        this.setNoAI(true);
        this.setSilent(true);
        this.setCanPickupLoot(false);
        this.setUUID(uuid);
    }

}
