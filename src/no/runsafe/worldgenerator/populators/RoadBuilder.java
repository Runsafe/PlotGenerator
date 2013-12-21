package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class RoadBuilder extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		if(chunk.getX() % PlotChunkGenerator.PLOT_SIZE == 0)
			apply(false, chunk);

		if(chunk.getZ() % PlotChunkGenerator.PLOT_SIZE == 0)
			apply(true, chunk);
	}

	private void apply(boolean flip, Chunk chunk)
	{
		for (int x = 7; x <= 13; ++x)
			for (int y = 62; y <= 64; ++y)
				for (int z = 1; z <= 16; ++z)
					chunk.getBlock(flip ? z : x, y, flip ? x : z).setType(crossection[2 - y + 62][x - 7]);
	}


	private final Material[][] crossection = new Material[][]{
		{Material.STONE, Material.AIR,   Material.STEP,  Material.STEP,  Material.STEP,  Material.AIR,   Material.STONE},
		{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE}
	};
}
