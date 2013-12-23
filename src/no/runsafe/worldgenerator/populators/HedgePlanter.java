package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class HedgePlanter extends BlockPopulator
{
	public HedgePlanter(PlotChunkGenerator generator)
	{
		this.generator = generator;
	}

	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		if (generator.getMode() != PlotChunkGenerator.Mode.NORMAL)
			return;

		boolean hRoad = chunk.getX() % PlotChunkGenerator.PLOT_SIZE == 0;
		boolean vRoad = chunk.getZ() % PlotChunkGenerator.PLOT_SIZE == 0;

		if (hRoad)
			plant(chunk, true);

		if (vRoad)
			plant(chunk, false);
	}

	private void plant(Chunk chunk, boolean rotate)
	{
		int Z_FROM = 0;
		int Z_TO = Z_FROM + 15;
		int[] X_VALUES = new int[]{5, 13};
		for (int x : X_VALUES)
			for (int z = Z_FROM; z <= Z_TO; ++z)
			{
				chunk.getBlock(rotate ? z : x, 66, rotate ? x : z).setType(Material.LEAVES);
				chunk.getBlock(rotate ? z : x, 65, rotate ? x : z).setType(Material.LEAVES);
				chunk.getBlock(rotate ? z : x, 64, rotate ? x : z).setType(Material.LOG);
			}
	}

	private final PlotChunkGenerator generator;
}
