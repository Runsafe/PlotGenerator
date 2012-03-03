// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   KruGeneration.java

package me.Kruithne.KruGeneration;

import java.util.logging.Logger;
import org.bukkit.generator.ChunkGenerator;
import org.bukkit.plugin.java.JavaPlugin;

// Referenced classes of package me.kruithne.KruGeneration:
//            KruGenerationGenerator

public class KruGeneration extends JavaPlugin
{

    public KruGeneration()
    {
        log = Logger.getLogger("Minecraft");
    }

    public void onDisable()
    {
    }

    public void onEnable()
    {
        log.info("This stuff is all like enabled and things.");
    }

    public ChunkGenerator getDefaultWorldGenerator(String worldName, String id)
    {
        return new KruGenerationGenerator();
    }

    Logger log;
}
