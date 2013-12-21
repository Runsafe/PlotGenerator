package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class IntersectionBuilder extends Road
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		boolean hRoad = chunk.getX() % PlotChunkGenerator.PLOT_SIZE == 0;
		boolean vRoad = chunk.getZ() % PlotChunkGenerator.PLOT_SIZE == 0;

		if (hRoad && vRoad)
			apply(chunk);
	}

	private void apply(Chunk chunk)
	{
		// First lay down the basic road
		baseRoad(chunk, false);
		baseRoad(chunk, true);

		// Next, clean it up
		cleanIntersection(chunk);
	}

	private void baseRoad(Chunk chunk, boolean flip)
	{
		int Z_FROM = 0;
		int Z_TO = Z_FROM + 15;
		for (int z = Z_FROM; z <= Z_TO; ++z)
		{
			int X_FROM = 6;
			int X_TO = X_FROM + crossectionbase[0].length - 1;
			int Y_FROM = 62;
			int Y_TO = Y_FROM + crossectionbase.length - 1;
			for (int x = X_FROM; x <= X_TO; ++x)
				for (int y = Y_FROM; y <= Y_TO; ++y)
				{
					Block block = chunk.getBlock(flip ? z : x, y, flip ? x : z);
					switch (block.getType())
					{
						case STEP:
							break;
						case AIR:
						case WATER:
							if (y < 64 && (x - X_FROM < 2 || X_TO - x < 2))
								break;
						default:
							block.setType(crossectionbase[(crossectionbase.length - 1) - y + Y_FROM][x - X_FROM]);
					}
				}
		}
	}

	private void cleanIntersection(Chunk chunk)
	{
		for(int x = 8; x <= 10; ++x)
			for(int z = 0; z <= 15; ++z)
			{
				chunk.getBlock(x, 65, z).setType(Material.AIR);
				chunk.getBlock(z, 65, x).setType(Material.AIR);
			}
	}
}
