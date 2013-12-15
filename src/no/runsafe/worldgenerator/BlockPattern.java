package no.runsafe.worldgenerator;

import com.google.common.collect.Lists;
import org.bukkit.Material;

import java.util.List;

class BlockPattern
{
	BlockPattern(int[] blockData)
	{
		this.blockData = blockData;
	}

	public Material getMaterial(int x, int y, int z, boolean rotate)
	{
		if (rotate)
			return palette.get(blockData[x + z * 16 + y * 256]);

		return palette.get(blockData[x * 16 + z + y * 256]);
	}

	public static List<Material> palette = Lists.newArrayList(
		Material.AIR,
		Material.BEDROCK,
		Material.STONE,
		Material.GRASS,
		Material.DIRT,
		Material.STEP,
		Material.LOG,
		Material.LEAVES,
		Material.FENCE,
		Material.TORCH,
		Material.GLOWSTONE,
		Material.WATER,
		Material.DOUBLE_STEP
	);
	public static int EMPTY = palette.indexOf(Material.AIR);
	public static int BEDRK = palette.indexOf(Material.BEDROCK);
	public static int STONE = palette.indexOf(Material.STONE);
	public static int GRASS = palette.indexOf(Material.GRASS);
	public static int EARTH = palette.indexOf(Material.DIRT);
	public static int HSTEP = palette.indexOf(Material.STEP);
	public static int WOODL = palette.indexOf(Material.LOG);
	public static int LEAFS = palette.indexOf(Material.LEAVES);
	public static int FENCE = palette.indexOf(Material.FENCE);
	public static int TORCH = palette.indexOf(Material.TORCH);
	public static int GLWST = palette.indexOf(Material.GLOWSTONE);
	public static int WATER = palette.indexOf(Material.WATER);
	public static int DBLST = palette.indexOf(Material.DOUBLE_STEP);

	protected final int[] blockData;
}