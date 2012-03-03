package me.Kruithne.KruGeneration;

import java.util.Random;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;

public class KruGenerationGenerator extends ChunkGenerator
{

    public KruGenerationGenerator()
    {
    }

    public byte[] generate(World world, Random random, int cx, int cz)
    {
        byte result[] = new byte[32768];
        for(int x = 0; x < 16; x++)
        {
            for(int z = 0; z < 16; z++)
            {
                int height = 70;
                for(int y = 0; y < height; y++)
                {
                    int block = (x * 16 + z) * 128 + y;
                    int plotSize = 3;
                    byte blockID = (byte)Material.AIR.getId();
                    if(y == 64)
                        blockID = (byte)Material.GRASS.getId();
                    if(y == 0)
                        blockID = (byte)Material.BEDROCK.getId();
                    else
                    if(y > 59 && y < 64)
                        blockID = (byte)Material.DIRT.getId();
                    else
                    if(y < 60)
                        blockID = (byte)Material.STONE.getId();
                    if(cx % plotSize == 0 && cz % plotSize == 0)
                    {
                        if(x > 7 && x < 11 || z > 7 && z < 11)
                            if(y == 63)
                                blockID = (byte)Material.STONE.getId();
                            else
                            if(y == 64)
                                blockID = (byte)Material.STEP.getId();
                        if((x == 5 || x == 13) && (z < 6 || z > 12))
                            if(y == 64)
                                blockID = (byte)Material.LOG.getId();
                            else
                            if(y == 65 || y == 66)
                                blockID = (byte)Material.LEAVES.getId();
                        if((z == 5 || z == 13) && (x < 6 || x > 12))
                            if(y == 64)
                                blockID = (byte)Material.LOG.getId();
                            else
                            if(y == 65 || y == 66)
                                blockID = (byte)Material.LEAVES.getId();
                        if((x == 6 || x == 7 || x == 11 || x == 12) && (z < 8 || z > 10) && y == 65)
                            blockID = (byte)Material.STEP.getId();
                        if((z == 6 || z == 7 || z == 11 || z == 12) && (x < 8 || x > 10) && y == 65)
                            blockID = (byte)Material.STEP.getId();
                        if((x == 7 || x == 11) && (z < 8 || z > 10))
                            if(y == 64)
                                blockID = (byte)Material.AIR.getId();
                            else
                            if(y == 63)
                                blockID = (byte)Material.WATER.getId();
                            else
                            if(y == 62)
                                blockID = (byte)Material.STONE.getId();
                        if((z == 7 || z == 11) && (x < 8 || x > 10))
                            if(y == 64)
                                blockID = (byte)Material.AIR.getId();
                            else
                            if(y == 63)
                                blockID = (byte)Material.WATER.getId();
                            else
                            if(y == 62)
                                blockID = (byte)Material.STONE.getId();
                        if(x == 7 && z == 7 || x == 11 && z == 7 || x == 7 && z == 11 || x == 11 && z == 11)
                            if(y == 65)
                                blockID = 43;
                            else
                            if(y == 66)
                                blockID = (byte)Material.FENCE.getId();
                            else
                            if(y == 67)
                                blockID = (byte)Material.TORCH.getId();
                        if((x == 6 || x == 12) && (z < 7 || z > 11) && (y == 64 || y == 63))
                            blockID = (byte)Material.STONE.getId();
                        if((z == 6 || z == 12) && (x < 7 || x > 11) && (y == 64 || y == 63))
                            blockID = (byte)Material.STONE.getId();
                        if((x == 6 || x == 12) && (z == 6 || z == 12) && y == 64)
                            blockID = (byte)Material.GLOWSTONE.getId();
                        if(x == 4 && z == 14 && y == 65)
                            blockID = (byte)Material.LOG.getId();
                    } else
                    if(cx % plotSize == 0 && cz % plotSize != 0)
                    {
                        if(x > 7 && x < 11)
                            if(y == 63)
                                blockID = (byte)Material.STONE.getId();
                            else
                            if(y == 64)
                                blockID = (byte)Material.STEP.getId();
                        if(x == 5 || x == 13)
                            if(y == 64)
                                blockID = (byte)Material.LOG.getId();
                            else
                            if(y == 65 || y == 66)
                                blockID = (byte)Material.LEAVES.getId();
                        if((x == 6 || x == 7 || x == 11 || x == 12) && y == 65)
                            blockID = (byte)Material.STEP.getId();
                        if(x == 7 || x == 11)
                            if(y == 64)
                                blockID = (byte)Material.AIR.getId();
                            else
                            if(y == 63)
                                blockID = (byte)Material.WATER.getId();
                            else
                            if(y == 62)
                                blockID = (byte)Material.STONE.getId();
                        if((x == 6 || x == 12) && (y == 64 || y == 63))
                            blockID = (byte)Material.STONE.getId();
                        if((x == 6 || x == 12) && (z == 5 || z == 13) && y == 64)
                            blockID = (byte)Material.GLOWSTONE.getId();
                        if((x == 7 || x == 11) && (z == 5 || z == 13))
                            if(y == 65)
                                blockID = 43;
                            else
                            if(y == 66)
                                blockID = (byte)Material.FENCE.getId();
                            else
                            if(y == 67)
                                blockID = (byte)Material.TORCH.getId();
                    } else
                    if(cx % plotSize != 0 && cz % plotSize == 0)
                    {
                        if(z > 7 && z < 11)
                            if(y == 63)
                                blockID = (byte)Material.STONE.getId();
                            else
                            if(y == 64)
                                blockID = (byte)Material.STEP.getId();
                        if(z == 5 || z == 13)
                            if(y == 64)
                                blockID = (byte)Material.LOG.getId();
                            else
                            if(y == 65 || y == 66)
                                blockID = (byte)Material.LEAVES.getId();
                        if((z == 6 || z == 7 || z == 11 || z == 12) && y == 65)
                            blockID = (byte)Material.STEP.getId();
                        if(z == 7 || z == 11)
                            if(y == 64)
                                blockID = (byte)Material.AIR.getId();
                            else
                            if(y == 63)
                                blockID = (byte)Material.WATER.getId();
                            else
                            if(y == 62)
                                blockID = (byte)Material.STONE.getId();
                        if((z == 6 || z == 12) && (y == 64 || y == 63))
                            blockID = (byte)Material.STONE.getId();
                        if((z == 6 || z == 12) && (x == 5 || x == 13) && y == 64)
                            blockID = (byte)Material.GLOWSTONE.getId();
                        if((z == 7 || z == 11) && (x == 5 || x == 13))
                            if(y == 65)
                                blockID = 43;
                            else
                            if(y == 66)
                                blockID = (byte)Material.FENCE.getId();
                            else
                            if(y == 67)
                                blockID = (byte)Material.TORCH.getId();
                    } else
                    if(cx % plotSize != 0)
                    {
                        //int _tmp = cz % plotSize;
                    }
                    result[block] = blockID;
                }

            }

        }

        return result;
    }

    public Location getFixedSpawnLocation(World world, Random random)
    {
        if(!world.isChunkLoaded(0, 0))
            world.loadChunk(0, 0);
        if(world.getHighestBlockYAt(0, 0) <= 0 && world.getBlockAt(0, 0, 0).getType() == Material.AIR)
            return new Location(world, 0.0D, 64D, 0.0D);
        else
            return new Location(world, 0.0D, world.getHighestBlockYAt(0, 0), 0.0D);
    }
}
