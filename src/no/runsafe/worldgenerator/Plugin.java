package no.runsafe.worldgenerator;

import no.runsafe.framework.RunsafeConfigurablePlugin;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafeConfigurablePlugin
{
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		return getComponent(PlotChunkGenerator.class);
	}

	@Override
	protected void PluginSetup()
	{
		addComponent(PlotChunkGenerator.class);
	}
}
