package no.runsafe.worldgenerator;

import com.google.common.collect.Lists;
import no.runsafe.worldgenerator.populators.IntersectionBuilder;
import no.runsafe.worldgenerator.populators.StraightRoadBuilder;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;
import org.bukkit.generator.ChunkGenerator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class PlotChunkGenerator extends ChunkGenerator implements IPlotGenerator
{
	public final static int PLOT_SIZE = 3;

	public enum Mode
	{
		NORMAL, FLAT, VOID
	}

	public PlotChunkGenerator()
	{
		mode = Mode.NORMAL;
	}

	@Override
	public void setMode(Mode generatorMode)
	{
		mode = generatorMode;
	}

	@Override
	public List<BlockPopulator> getDefaultPopulators(World world)
	{
		if (mode == Mode.NORMAL)
		{
			List<BlockPopulator> populators = new ArrayList<BlockPopulator>();
			populators.add(new StraightRoadBuilder());
			populators.add(new IntersectionBuilder());
			return populators;
		}
		return Lists.newArrayList();
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes)
	{
		byte[] result = null;

		switch (mode)
		{
			case NORMAL:
				result = NormalGenerator();
				break;
			case FLAT:
				result = FlatGenerator();
				break;
			case VOID:
				result = VoidGenerator();
				break;
		}

		byte[][] chunk = new byte[8][4096];
		for (int x = 0; x < 16; ++x)
			for (int y = 0; y < 128; ++y)
				for (int z = 0; z < 16; ++z)
					chunk[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = result[(x * 16 + z) * 128 + y];

		return chunk;
	}

	private byte[] VoidGenerator()
	{
		byte result[] = new byte[32768];
		Arrays.fill(result, (byte) Material.AIR.getId());
		return result;
	}

	private byte[] FlatGenerator()
	{
		byte result[] = new byte[32768];
		Arrays.fill(result, (byte) 0);
		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				int offset = (x * 16 + z) * 128;
				result[offset] = (byte) Material.BEDROCK.getId();
				Arrays.fill(result, offset + 1, offset + 60, (byte) Material.STONE.getId());
				Arrays.fill(result, offset + 60, offset + 63, (byte) Material.DIRT.getId());
				result[offset + 63] = (byte) Material.GRASS.getId();
			}
		}
		return result;
	}

	private byte[] NormalGenerator()
	{
		byte result[] = new byte[32768];
		Arrays.fill(result, (byte) Material.AIR.getId());

		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				int offset = (x * 16 + z) * 128;
				result[offset] = (byte) Material.BEDROCK.getId();
				Arrays.fill(result, offset + 1, offset + 60, (byte) Material.STONE.getId());
				Arrays.fill(result, offset + 60, offset + 64, (byte) Material.DIRT.getId());
				result[offset + 64] = (byte) Material.GRASS.getId();
			}
		}
		return result;
	}

	public Location getFixedSpawnLocation(World world, Random random)
	{
		if (!world.isChunkLoaded(0, 0))
			world.loadChunk(0, 0);
		if (world.getHighestBlockYAt(0, 0) <= 0 && world.getBlockAt(0, 0, 0).getType() == Material.AIR)
			return new Location(world, 0.0D, 64D, 0.0D);
		else
			return new Location(world, 0.0D, world.getHighestBlockYAt(0, 0), 0.0D);
	}

	private Mode mode;
}
