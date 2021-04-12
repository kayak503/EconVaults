package com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2;

import com.google.common.collect.Sets;
import com.mojang.authlib.GameProfile;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import net.minecraft.server.v1_16_R2.*;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
import org.bukkit.entity.Player;

import javax.annotation.Nullable;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

public class NPC_Teller extends EntityVillager {
    public NPC_Teller(VaultData vault) {
        super(EntityTypes.VILLAGER, ((CraftWorld) EconVaults.OverWorld).getHandle());
        this.setPosition(vault.GetTellerX(), vault.GetTellerY(), vault.GetTellerZ());
        this.setCustomName(new ChatComponentText(ChatColor.GOLD + "" + ChatColor.BOLD + vault.GetName() +"'s Teller"));
        this.setCustomNameVisible(true);

        this.setUUID(vault.GetUuid());
        this.setInvulnerable(true);
        this.setCanPickupLoot(false);
        this.setSilent(true);
        this.removeAI();


    }

    private void removeAI() {
        try {
            Field availableGoalsField = PathfinderGoalSelector.class.getDeclaredField("d"); //linked hash set type
            Field priorityBehaviorsField = BehaviorController.class.getDeclaredField("e"); //map type
            Field coreActivitysField = BehaviorController.class.getDeclaredField("i"); //hash set type

            availableGoalsField.setAccessible(true);
            priorityBehaviorsField.setAccessible(true);
            coreActivitysField.setAccessible(true);

            availableGoalsField.set(this.goalSelector, Sets.newLinkedHashSet());
            availableGoalsField.set(this.targetSelector, Sets.newLinkedHashSet());
            priorityBehaviorsField.set(this.getBehaviorController(), Collections.emptyMap());
            coreActivitysField.set(this.getBehaviorController(), Sets.newHashSet());


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean isCollidable() {
        return false;
    }

}


   /* private static List<EntityPlayer> Tellers = new ArrayList<EntityPlayer>();

    public static void createVaultTeller(Player player, VaultData vault){
        MinecraftServer server = ((CraftServer) Bukkit.getServer()).getServer();
        WorldServer world = ((CraftWorld) Bukkit.getWorld(player.getWorld().getName())).getHandle();

        GameProfile gameProfile = new GameProfile(vault.GetUuid(), "");
        EntityPlayer npc = new EntityPlayer( server, world, gameProfile, new PlayerInteractManager(world));
        npc.setLocation(vault.GetTellerX(),vault.GetTellerY(),vault.GetTellerZ(), 0,0);
        Tellers.add(npc);
        sendPacketTellerToOnlinePlayers(npc);

    }
    public static void sendPacketTeller(EntityPlayer npc, Player player){
        PlayerConnection connection = ((CraftPlayer)player).getHandle().playerConnection;

        connection.sendPacket(new PacketPlayOutPlayerInfo(PacketPlayOutPlayerInfo.EnumPlayerInfoAction.ADD_PLAYER,  npc));
        connection.sendPacket(new PacketPlayOutNamedEntitySpawn(npc));
        connection.sendPacket(new PacketPlayOutEntityHeadRotation(npc,(byte) (npc.yaw *256/360)));
        EconVaults.playerList.hidePlayer((Player) npc);
    }

    public static void sendPacketTellerToOnlinePlayers(EntityPlayer npc){
        for (Player player:Bukkit.getOnlinePlayers()){
            sendPacketTeller(npc,player);
        }
    }
    public static void sendPacketTellerToPlayer(Player player){
        for (EntityPlayer npc:Tellers){
            sendPacketTeller(npc,player);
        }
    }

    public static List<EntityPlayer> getTellersList(){
        return Tellers;
    }


    public static void t(){

    }

*/


