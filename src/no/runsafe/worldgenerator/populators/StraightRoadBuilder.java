package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class StraightRoadBuilder extends Road
{
	public StraightRoadBuilder(PlotChunkGenerator generator)
	{
		super(generator);
	}

	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		if (generator.getMode() != PlotChunkGenerator.Mode.NORMAL)
			return;

		boolean hRoad = chunk.getX() % PlotChunkGenerator.PLOT_SIZE == 0;
		boolean vRoad = chunk.getZ() % PlotChunkGenerator.PLOT_SIZE == 0;

		if (vRoad == hRoad)
			return;

		if (hRoad)
			apply(false, chunk);

		if (vRoad)
			apply(true, chunk);
	}

	private void apply(boolean flip, Chunk chunk)
	{
		int Z_FROM = 0;
		int Z_TO = Z_FROM + 15;
		for (int z = Z_FROM; z <= Z_TO; ++z)
		{
			Material[][] crossSection = ((z + 3) % 8 == 0) ? crossSectionLight : crossSectionBase;
			int X_FROM = 6;
			int X_TO = X_FROM + crossSection[0].length - 1;
			int Y_FROM = 62;
			int Y_TO = Y_FROM + crossSection.length - 1;
			for (int x = X_FROM; x <= X_TO; ++x)
				for (int y = Y_FROM; y <= Y_TO; ++y)
				{
					Block block = chunk.getBlock(flip ? z : x, y, flip ? x : z);
					block.setType(crossSection[(crossSection.length - 1) - y + Y_FROM][x - X_FROM]);
				}
		}
	}
}
