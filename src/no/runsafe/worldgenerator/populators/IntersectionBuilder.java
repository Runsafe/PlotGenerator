package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Block;

import java.util.Random;

public class IntersectionBuilder extends Road
{
	public IntersectionBuilder(PlotChunkGenerator generator)
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

		if (hRoad && vRoad)
			apply(chunk);
	}

	private void apply(Chunk chunk)
	{
		// First lay down the basic road
		baseRoad(chunk, false);
		baseRoad(chunk, true);

		// Next, build the center
		cleanIntersection(chunk);
	}

	private void baseRoad(Chunk chunk, boolean flip)
	{
		int Z_FROM = 0;
		int Z_TO = Z_FROM + 15;
		for (int z = Z_FROM; z <= Z_TO; ++z)
		{
			int X_FROM = 6;
			int X_TO = X_FROM + crossSectionBase[0].length - 1;
			int Y_FROM = 62;
			int Y_TO = Y_FROM + crossSectionBase.length - 1;
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
							block.setType(crossSectionBase[(crossSectionBase.length - 1) - y + Y_FROM][x - X_FROM]);
					}
				}
		}
	}

	private void cleanIntersection(Chunk chunk)
	{
		int Y_FROM = 62;
		int Y_TO = Y_FROM + intersection.length - 1;
		int Z_FROM = 6;
		int Z_TO = Z_FROM + intersection[0].length - 1;
		int X_FROM = 6;
		int X_TO = X_FROM + intersection[0][0].length - 1;

		for (int x = X_FROM; x <= X_TO; ++x)
			for (int y = Y_FROM; y <= Y_TO; ++y)
				for (int z = Z_FROM; z <= Z_TO; ++z)
					chunk.getBlock(x, y, z).setType(intersection[(intersection.length - 1) - y + Y_FROM][z - Z_FROM][x - X_FROM]);
	}

	private final Material[][][] intersection = new Material[][][]{
		{
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.TORCH, Material.AIR, Material.AIR, Material.AIR, Material.TORCH, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.TORCH, Material.AIR, Material.AIR, Material.AIR, Material.TORCH, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR}
		},
		{
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.FENCE, Material.AIR, Material.AIR, Material.AIR, Material.FENCE, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.FENCE, Material.AIR, Material.AIR, Material.AIR, Material.FENCE, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR}
		},
		{
			{Material.STEP, Material.STEP, Material.AIR, Material.AIR, Material.AIR, Material.STEP, Material.STEP},
			{Material.STEP, Material.DOUBLE_STEP, Material.AIR, Material.AIR, Material.AIR, Material.DOUBLE_STEP, Material.STEP},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR, Material.AIR},
			{Material.STEP, Material.DOUBLE_STEP, Material.AIR, Material.AIR, Material.AIR, Material.DOUBLE_STEP, Material.STEP},
			{Material.STEP, Material.STEP, Material.AIR, Material.AIR, Material.AIR, Material.STEP, Material.STEP},
		},
		{
			{Material.GLOWSTONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.GLOWSTONE},
			{Material.AIR, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.AIR},
			{Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP},
			{Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP},
			{Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP, Material.STEP},
			{Material.AIR, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.AIR},
			{Material.GLOWSTONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.GLOWSTONE},
		},
		{
			{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
			{Material.WATER, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.WATER},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.WATER, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.WATER},
			{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		},
		{
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
			{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE},
		},
	};
}
