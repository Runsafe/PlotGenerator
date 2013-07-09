package no.runsafe.worldgenerator;

import no.runsafe.framework.api.IConfiguration;
import no.runsafe.framework.api.event.plugin.IConfigurationChanged;
import no.runsafe.framework.minecraft.RunsafeServer;
import no.runsafe.framework.minecraft.RunsafeWorld;
import org.bukkit.Chunk;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

import java.util.Arrays;
import java.util.Random;

public class PlotChunkGenerator extends ChunkGenerator implements IConfigurationChanged
{
	final static int PLOT_SIZE = 3;

	public enum Mode
	{
		NORMAL, FLAT, VOID, DEFAULT
	}

	public enum Biome
	{
		Swamp(org.bukkit.block.Biome.SWAMPLAND),
		Forest(org.bukkit.block.Biome.FOREST),
		Taiga(org.bukkit.block.Biome.TAIGA),
		Desert(org.bukkit.block.Biome.DESERT),
		Plains(org.bukkit.block.Biome.PLAINS),
		Hell(org.bukkit.block.Biome.HELL),
		Sky(org.bukkit.block.Biome.SKY),
		Ocean(org.bukkit.block.Biome.OCEAN),
		River(org.bukkit.block.Biome.RIVER),
		ExtremeHills(org.bukkit.block.Biome.EXTREME_HILLS),
		FrozenOcean(org.bukkit.block.Biome.FROZEN_OCEAN),
		FrozenRiver(org.bukkit.block.Biome.FROZEN_RIVER),
		IcePlains(org.bukkit.block.Biome.ICE_PLAINS),
		IceMountains(org.bukkit.block.Biome.ICE_MOUNTAINS),
		MushroomIsland(org.bukkit.block.Biome.MUSHROOM_ISLAND),
		MushroomShore(org.bukkit.block.Biome.MUSHROOM_SHORE),
		Beach(org.bukkit.block.Biome.BEACH),
		DesertHills(org.bukkit.block.Biome.DESERT_HILLS),
		ForestHills(org.bukkit.block.Biome.FOREST_HILLS),
		TaigaHills(org.bukkit.block.Biome.TAIGA_HILLS),
		SmallMountains(org.bukkit.block.Biome.SMALL_MOUNTAINS),
		Jungle(org.bukkit.block.Biome.JUNGLE),
		JungleHills(org.bukkit.block.Biome.JUNGLE_HILLS);

		Biome(org.bukkit.block.Biome bukkitBiome)
		{
			this.biome = bukkitBiome;
		}

		org.bukkit.block.Biome getRaw()
		{
			return biome;
		}

		private final org.bukkit.block.Biome biome;
	}

	public PlotChunkGenerator()
	{
		mode = Mode.NORMAL;
		straight = new StraightRoad();
		intersect = new CrossRoads();
	}

	public void setMode(Mode generatorMode)
	{
		mode = generatorMode;
	}

	public void setBiome(Biome biome)
	{
		biomeOverride = biome;
	}

	@Override
	public void OnConfigurationChanged(IConfiguration configuration)
	{
		String dummyWorld = configuration.getConfigValueAsString("dummyWorld");
		if (dummyWorld != null)
			dummy = RunsafeServer.Instance.getWorld(dummyWorld);
	}

	@Override
	public byte[][] generateBlockSections(World world, Random random, int cx, int cz, BiomeGrid biomes)
	{
		byte[] result = null;

		switch (mode)
		{
			case NORMAL:
				result = NormalGenerator(cx, cz);
				break;
			case FLAT:
				result = FlatGenerator();
				break;
			case VOID:
				result = VoidGenerator();
				break;
			case DEFAULT:
				if (dummy == null)
				{
					RunsafeServer.Instance.getDebugger().logError("Dummy world is null!");
					return null;
				}
				Chunk chunk = dummy.getRaw().getChunkAt(cx, cz);
				if (!chunk.isLoaded())
					chunk.load(false);
				chunk.getWorld().regenerateChunk(chunk.getX(), chunk.getZ());
				byte[][] blocks = new byte[8][4096];
				for (int x = 0; x < 16; ++x)
					for (int y = 0; y < 128; ++y)
						for (int z = 0; z < 16; ++z)
							blocks[y >> 4][((y & 0xF) << 8) | (z << 4) | x] = (byte) chunk.getBlock(x, y, z).getType().getId();
				return blocks;
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
		Arrays.fill(result, Blocks.AIR);
		return result;
	}

	private byte[] FlatGenerator()
	{
		byte result[] = new byte[32768];
		Arrays.fill(result, Blocks.AIR);
		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				int offset = (x * 16 + z) * 128;
				result[offset] = Blocks.BEDROCK;
				Arrays.fill(result, offset + 1, offset + 60, Blocks.STONE);
				Arrays.fill(result, offset + 60, offset + 63, Blocks.DIRT);
				result[offset + 63] = Blocks.GRASS;
			}
		}
		return result;
	}

	private byte[] NormalGenerator(int cx, int cz)
	{
		byte result[] = new byte[32768];
		Arrays.fill(result, Blocks.AIR);
		boolean hRoad = cx % PLOT_SIZE == 0;
		boolean vRoad = cz % PLOT_SIZE == 0;

		for (int x = 0; x < 16; ++x)
		{
			for (int z = 0; z < 16; ++z)
			{
				int offset = (x * 16 + z) * 128;
				result[offset] = Blocks.BEDROCK;
				Arrays.fill(result, offset + 1, offset + 60, Blocks.STONE);
				Arrays.fill(result, offset + 60, offset + 64, Blocks.DIRT);

				if (hRoad || vRoad)
				{
					for (int y = 0; y < 6; ++y)
					{
						byte what;
						if (hRoad && vRoad)
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
		if (!world.isChunkLoaded(0, 0))
			world.loadChunk(0, 0);
		if (world.getHighestBlockYAt(0, 0) <= 0 && world.getBlockAt(0, 0, 0).getType() == Material.AIR)
			return new Location(world, 0.0D, 64D, 0.0D);
		else
			return new Location(world, 0.0D, world.getHighestBlockYAt(0, 0), 0.0D);
	}

	private StraightRoad straight;
	private CrossRoads intersect;
	private Mode mode;
	private Biome biomeOverride = null;
	private RunsafeWorld dummy;
}
