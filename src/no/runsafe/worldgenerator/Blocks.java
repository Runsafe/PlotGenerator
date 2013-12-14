package no.runsafe.worldgenerator;

import com.google.common.collect.Lists;
import org.bukkit.Material;

import java.util.List;

class Blocks
{
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
	public static byte AIR = (byte) Material.AIR.getId();
	public static byte BEDROCK = (byte) Material.BEDROCK.getId();
	public static byte STONE = (byte) Material.STONE.getId();
	public static byte GRASS = (byte) Material.GRASS.getId();
	public static byte DIRT = (byte) Material.DIRT.getId();
	public static byte STEP = (byte) Material.STEP.getId();
	public static byte LOG = (byte) Material.LOG.getId();
	public static byte LEAVES = (byte) Material.LEAVES.getId();
	public static byte FENCE = (byte) Material.FENCE.getId();
	public static byte TORCH = (byte) Material.TORCH.getId();
	public static byte GLOWSTONE = (byte) Material.GLOWSTONE.getId();
	public static byte WATER = (byte) Material.WATER.getId();
	public static byte DOUBLESTEP = (byte) 43;
}