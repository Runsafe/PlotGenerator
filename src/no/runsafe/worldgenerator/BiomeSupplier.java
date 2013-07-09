package no.runsafe.worldgenerator;

import org.bukkit.block.Biome;
import org.bukkit.generator.ChunkGenerator;

public class BiomeSupplier implements ChunkGenerator.BiomeGrid
{
	public BiomeSupplier(Biome biome)
	{
		this.biome = biome;
	}

	@Override
	public Biome getBiome(int i, int i2)
	{
		return biome;
	}

	@Override
	public void setBiome(int i, int i2, Biome biome)
	{
	}

	private final Biome biome;
}
