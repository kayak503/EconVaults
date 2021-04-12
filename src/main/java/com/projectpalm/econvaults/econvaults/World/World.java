package com.projectpalm.econvaults.econvaults.World;

import com.projectpalm.econvaults.econvaults.Data.VaultData;
import com.projectpalm.econvaults.econvaults.EconVaults;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;

import java.util.Arrays;
import java.util.Map;
import java.util.TreeMap;

public class World {
    public static ItemStack[] GetVaultWorldArray(VaultData vault){

        // deal with 0/0 possibility

        Integer[][] x = vault.GetVaultPosition();
        Integer[] differance = new Integer[2];
        differance[0] = x[0][0] - x[1][0];
        differance[1] = x[0][1] - x[1][1];
        differance[2] = x[0][2] - x[1][2];

        Integer sum = Arrays.stream(differance).reduce(0, Integer::sum);

        ItemStack[] ReturnArray = new ItemStack[sum];
        int i = 0;

        int xLoopIterator = differance[0]/Math.abs(differance[0]);
        int yLoopIterator = differance[1]/Math.abs(differance[1]);
        int zLoopIterator = differance[2]/Math.abs(differance[2]);

        for (int yLoop = 0; yLoop != (differance[1] + yLoopIterator); yLoop += yLoopIterator){
            for (int xLoop = 0; xLoop != (differance[0] + xLoopIterator); xLoop += xLoopIterator){
                for (int zLoop = 0; zLoop != (differance[2] + zLoopIterator); zLoop += zLoopIterator){
                    ReturnArray[i] =new ItemStack(EconVaults.OverWorld.getBlockAt((x[0][0] + xLoop ),(x[0][1] + yLoop),(x[0][2] + zLoop)).getType());
                    i++;
                }
            }
        }

        return ReturnArray;
    }

    public static Map<Material,Integer> GetVaultBlockCount(VaultData vault,ItemStack[] itemStacks){
        Map<Material,Integer> BlockCount = vault.GetContentsMap();
        for (Material material : BlockCount.keySet()) {
            BlockCount.replace(material,0);
        }
        for (ItemStack item: itemStacks){
            if ( BlockCount.containsKey(item.getType())){
                BlockCount.put(item.getType(),BlockCount.get(item.getType())+1 );
            }
            else {
                BlockCount.put(item.getType(),1);
            }
        }
        return BlockCount;
    }


    public static Material[] GetVaultWorldArrayMaterial(VaultData vault){

        // deal with 0/0 possibility

        Integer[][] x = vault.GetVaultPosition();
        Integer[] differance = new Integer[2];
        differance[0] = x[0][0] - x[1][0];
        differance[1] = x[0][1] - x[1][1];
        differance[2] = x[0][2] - x[1][2];

        Integer sum = Arrays.stream(differance).reduce(0, Integer::sum);

        Material[] ReturnArray = new Material[sum];
        int i = 0;

        int xLoopIterator = differance[0]/Math.abs(differance[0]);
        int yLoopIterator = differance[1]/Math.abs(differance[1]);
        int zLoopIterator = differance[2]/Math.abs(differance[2]);

        for (int yLoop = 0; yLoop != (differance[1] + yLoopIterator); yLoop += yLoopIterator){
            for (int xLoop = 0; xLoop != (differance[0] + xLoopIterator); xLoop += xLoopIterator){
                for (int zLoop = 0; zLoop != (differance[2] + zLoopIterator); zLoop += zLoopIterator){
                    ReturnArray[i] = EconVaults.OverWorld.getBlockAt((x[0][0] + xLoop ),(x[0][1] + yLoop),(x[0][2] + zLoop)).getType();
                    i++;
                }
            }
        }

        return ReturnArray;
    }



    // vault contents will not be stored as a map rather an image with
    // all world changes must be async
}
