package no.runsafe.worldgenerator;

import no.runsafe.framework.RunsafePlugin;
import org.bukkit.generator.ChunkGenerator;

public class Plugin extends RunsafePlugin
{
	public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
	{
		if (defaultGenerator == null)
		{
			defaultGenerator = super.getDefaultWorldGenerator(worldName, id);
			getComponent(PlotChunkGenerator.class).setDefaultGenerator(defaultGenerator);
		}
		return getComponent(PlotChunkGenerator.class);
	}

	@Override
	protected void PluginSetup()
	{
		addComponent(PlotChunkGenerator.class);
	}

	private ChunkGenerator defaultGenerator;
}
