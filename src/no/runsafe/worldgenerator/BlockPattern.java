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
			return materials.get(blockData[x + z * 16 + y * 256]);

		return materials.get(blockData[x * 16 + z + y * 256]);
	}

	public static List<Material> materials = Lists.newArrayList(
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
	public static int EMPTY = materials.indexOf(Material.AIR);
	public static int BEDRK = materials.indexOf(Material.BEDROCK);
	public static int STONE = materials.indexOf(Material.STONE);
	public static int GRASS = materials.indexOf(Material.GRASS);
	public static int EARTH = materials.indexOf(Material.DIRT);
	public static int HSTEP = materials.indexOf(Material.STEP);
	public static int WOODL = materials.indexOf(Material.LOG);
	public static int LEAFS = materials.indexOf(Material.LEAVES);
	public static int FENCE = materials.indexOf(Material.FENCE);
	public static int TORCH = materials.indexOf(Material.TORCH);
	public static int GLWST = materials.indexOf(Material.GLOWSTONE);
	public static int WATER = materials.indexOf(Material.WATER);
	public static int DBLST = materials.indexOf(Material.DOUBLE_STEP);

	protected final int[] blockData;
}