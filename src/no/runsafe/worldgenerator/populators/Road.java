package no.runsafe.worldgenerator.populators;

import no.runsafe.worldgenerator.PlotChunkGenerator;
import org.bukkit.Material;
import org.bukkit.generator.BlockPopulator;

public abstract class Road extends BlockPopulator
{
	protected Road(PlotChunkGenerator generator)
	{
		this.generator = generator;
	}

	protected final Material[][] crossectionbase = new Material[][]{
		{Material.STEP, Material.STEP, Material.AIR, Material.AIR, Material.AIR, Material.STEP, Material.STEP},
		{Material.STONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.STONE},
		{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE}
	};
	protected final Material[][] crossectionlight = new Material[][]{
		{Material.AIR, Material.TORCH, Material.AIR, Material.AIR, Material.AIR, Material.TORCH, Material.AIR},
		{Material.AIR, Material.FENCE, Material.AIR, Material.AIR, Material.AIR, Material.FENCE, Material.AIR},
		{Material.STEP, Material.DOUBLE_STEP, Material.AIR, Material.AIR, Material.AIR, Material.DOUBLE_STEP, Material.STEP},
		{Material.GLOWSTONE, Material.AIR, Material.STEP, Material.STEP, Material.STEP, Material.AIR, Material.GLOWSTONE},
		{Material.STONE, Material.WATER, Material.STONE, Material.STONE, Material.STONE, Material.WATER, Material.STONE},
		{Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE, Material.STONE}
	};

	protected final PlotChunkGenerator generator;
}
