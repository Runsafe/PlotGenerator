package me.Kruithne.KruGeneration;

import java.util.Arrays;
import java.util.Random;
import org.bukkit.*;
import org.bukkit.generator.ChunkGenerator;

public class KruGenerationGenerator extends ChunkGenerator
{
	private int plotSize = 3;

	private StraightRoad straight;
	private CrossRoads intersect;

    public KruGenerationGenerator()
    {
		straight = new StraightRoad();
		intersect = new CrossRoads();
    }

    public byte[] generate(World world, Random random, int cx, int cz)
    {
        byte result[] = new byte[32768];
		Arrays.fill(result, Blocks.AIR);

		boolean hRoad = cx % plotSize == 0;
		boolean vRoad = cz % plotSize == 0;

		for(int x = 0; x < 16; ++x)
		{
			for(int z = 0; z < 16; ++z)
			{
				int offset = (x * 16 + z) * 128;
				result[offset] = Blocks.BEDROCK;
				Arrays.fill(result, offset +  1, offset + 60, Blocks.STONE);
				Arrays.fill(result, offset + 60, offset + 64, Blocks.DIRT);

				if(hRoad || vRoad)
				{
					for(int y = 0; y < 6; ++y)
					{
						byte what = 0;
						if(hRoad && vRoad)
							what = intersect.getByte(x, y, z);

						else
							what = straight.getByte(x, y, z, hRoad);

						result[offset + 62 + y] = what;
					}
				}
				else
					result[offset + 64] = Blocks.GRASS;
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
