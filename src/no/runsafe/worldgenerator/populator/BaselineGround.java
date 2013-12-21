package no.runsafe.worldgenerator.populator;

import org.bukkit.Chunk;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.generator.BlockPopulator;

import java.util.Random;

public class BaselineGround extends BlockPopulator
{
	@Override
	public void populate(World world, Random random, Chunk chunk)
	{
		for (int x = 1; x <= 16; ++x)
			for (int z = 1; z <= 16; ++z)
			{
				chunk.getBlock(x, 1, z).setType(Material.BEDROCK);
				for (int y = 2; y <= 64; ++y)
					chunk.getBlock(x, y, z).setType(y < 61 ? Material.STONE : Material.DIRT);
				chunk.getBlock(x, 65, z).setType(Material.GRASS);
			}
	}
}
