package no.runsafe.worldgenerator;

import no.runsafe.framework.RunsafePlugin;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafePlugin
{
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return getComponent(PlotChunkGenerator.class);
	}

	@Override
	protected void PluginSetup()
	{
		exportAPI(PlotChunkGenerator.class);
	}
}
