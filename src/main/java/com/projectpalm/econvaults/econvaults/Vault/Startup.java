package com.projectpalm.econvaults.econvaults.Vault;

import com.projectpalm.econvaults.econvaults.Data.Data;
import com.projectpalm.econvaults.econvaults.GUI.GuiMethods;

import java.util.UUID;

public class Startup {
    // Runs all Methods that are required to Start Econ Vaults
    public static void StartUpEconVaults() {
        Data.RunDiagnostics(); // Data Class initialization

        Data.GetVaultByUuid(UUID.randomUUID()); // until error solved checks for code compilation
        Data.UpdateNetMoneyCount();

        GuiMethods.StartUp(); // sets up playerHead maps
    }

}
