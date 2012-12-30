package no.runsafe.worldgenerator;

import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

public class PlotGenerator extends JavaPlugin
{
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return new PlotChunkGenerator();
	}
}
