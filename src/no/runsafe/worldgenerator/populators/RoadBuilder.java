package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class RoadBuilder extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		if (chunk.getX() % PlotChunkGenerator.PLOT_SIZE == 0)
			apply(false, chunk);

		if (chunk.getZ() % PlotChunkGenerator.PLOT_SIZE == 0)
			apply(true, chunk);
	}

	private void apply(boolean flip, Chunk chunk)
	{
		int Z_FROM = 0;
		int Z_TO = Z_FROM + 15;
		for (int z = Z_FROM; z <= Z_TO; ++z)
		{
			Material[][] crossection = (z + 5 % 8 == 0) ? crossectionlight : crossectionbase;
			int X_FROM = 6;
			int X_TO = X_FROM + crossection[0].length - 1;
			int Y_FROM = 62;
			int Y_TO = Y_FROM + crossection.length - 1;
			for (int x = X_FROM; x <= X_TO; ++x)
				for (int y = Y_FROM; y <= Y_TO; ++y)
				{
					Block block = chunk.getBlock(flip ? z : x, y, flip ? x : z);
					switch (block.getType())
					{
						case WATER:
						case AIR:
						case STEP:
							if (x - X_FROM < 2 || X_TO - x < 2)
								break;
						default:
							block.setType(crossection[(crossection.length - 1) - y + Y_FROM][x - X_FROM]);
							break;
					}
				}
		}
	}

	private final Material[][] crossectionbase = new Material[][]{
		{Material.STEP, Material.STEP, Material.AIR, Material.AIR, Material.AIR, Material.STEP, Material.STEP},
		{Material.STONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.STONE},
		{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE}
	};

	private final Material[][] crossectionlight = new Material[][]{
		{Material.AIR, Material.TORCH, Material.AIR, Material.AIR, Material.AIR, Material.TORCH, Material.AIR},
		{Material.AIR, Material.FENCE, Material.AIR, Material.AIR, Material.AIR, Material.FENCE, Material.AIR},
		{Material.STEP, Material.DOUBLE_STEP, Material.AIR, Material.AIR, Material.AIR, Material.DOUBLE_STEP, Material.STEP},
		{Material.GLOWSTONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.GLOWSTONE},
		{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE}
	};

}
