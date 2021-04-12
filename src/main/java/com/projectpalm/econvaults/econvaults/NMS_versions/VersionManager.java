package com.projectpalm.econvaults.econvaults.NMS_versions;

import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.NMS_versions.v1_16_R2.NPC_Teller;
import org.bukkit.entity.Player;

import java.util.Map;
import java.util.TreeMap;
import java.util.UUID;

public interface VersionManager {
     Boolean isInTellerMap(UUID uuid);
     void spawnTeller(VaultData vault);
     void killTeller(UUID uuid);




}
