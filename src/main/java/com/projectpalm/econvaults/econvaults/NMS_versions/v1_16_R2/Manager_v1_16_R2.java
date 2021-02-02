package com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2;

import com.projectpalm.econvaults.econvaults.NMS_versions.VersionManager;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

import java.util.UUID;

public class Manager_v1_16_R2 implements VersionManager
{
    public int spawnTeller(UUID uuid, Player player){
        Mob_Teller mob = new Mob_Teller(player.getLocation(), uuid);
        WorldServer world = ((CraftWorld) player.getWorld()).getHandle();
        world.addEntity(mob);
        return 0;
    }

}
