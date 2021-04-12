package com.projectpalm.econvaults.econvaults;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.GUI.GuiMethods;

import java.util.UUID;

public class Startup {
    // Runs all Methods that are required to Start Econ Vaults
    public static void StartUpEconVaults() {
        Data.RunDiagnostics(); // Data Class initialization
        Data.GetVaultByUuid(UUID.randomUUID()); // until error solved checks for code compilation
        Data.UpdateNetMoneyCount();
        GuiMethods.StartUp(); // sets up playerHead maps

        // Spawn in all Tellers
        VaultData AllVaults[] = Data.GetAllVaults();
        for (int i = 0; i < AllVaults.length; i++){
            EconVaults.getInstance.tab.spawnTeller(AllVaults[i]);
        }

    }

}
