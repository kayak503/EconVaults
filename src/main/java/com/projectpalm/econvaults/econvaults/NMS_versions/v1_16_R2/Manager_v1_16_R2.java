package com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2;

import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import com.projectpalm.econvaults.econvaults.NMS_versions.VersionManager;
import net.minecraft.server.v1_16_R2.EntityPlayer;
import net.minecraft.server.v1_16_R2.PathfinderGoalLookAtPlayer;
import net.minecraft.server.v1_16_R2.WorldServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.UUID;

public class Manager_v1_16_R2 implements VersionManager
{
    private static Map<UUID,NPC_Teller> TellersMap = new TreeMap<UUID,NPC_Teller>();

    public void spawnTeller(VaultData vault){
        if (TellersMap.get(vault.GetUuid()) != null){killTeller(vault.GetUuid());}

        NPC_Teller npc = new NPC_Teller(vault);
        WorldServer world = ((CraftWorld) EconVaults.OverWorld).getHandle();
        world.addEntity(npc);
        npc.goalSelector.a(0, new PathfinderGoalLookAtPlayer(npc, EntityPlayer.class, 5.0f, 100));
        TellersMap.put(vault.GetUuid(),npc);
    }
    public void killTeller(UUID uuid){
        NPC_Teller npc = TellersMap.get(uuid);
        if (npc != null){
            npc.killEntity();
            TellersMap.remove(uuid);
        }

    }

    public Boolean isInTellerMap(UUID uuid){
        if (TellersMap.containsKey(uuid)){
            return true;
        }
        return false;
    }


}
