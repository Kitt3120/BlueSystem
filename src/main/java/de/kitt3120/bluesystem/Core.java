package de.kitt3120.bluesystem;

import de.kitt3120.bluesystem.commands.RemoveName;
import de.kitt3120.bluesystem.commands.SetPrefix;
import de.kitt3120.bluesystem.commands.SetSuffix;
import de.kitt3120.bluesystem.listeners.*;
import de.kitt3120.bluesystem.manage.FileManager;
import de.kitt3120.bluesystem.misc.HunkaKittLoveParticles;
import org.bukkit.plugin.java.JavaPlugin;

/**
 * Created by kitt3120 on 06.09.2017 at 19:28.
 */
public class Core extends JavaPlugin {

    private static Core instance;

    private FileManager fileManager;

    @Override
    public void onEnable() {
        instance = this;
        long millis = System.currentTimeMillis();
        getLogger().info("----------\n");
        getLogger().info("Starting Blue-System version " + getDescription().getVersion() + " coded by " + getDescription().getAuthors().toString());

        //Managers
        getLogger().info("Registering Managers...");
        fileManager = new FileManager();

        //Listeners
        getLogger().info("Registering Listeners...");
        new TreeChopper();
        new CreeperRepairer();
        new PetGodmode();
        new FriendlyWolfes();
        new CreativeCheatListener();
        new JoinNameFixer();
        new BlockLogger();
        new ExplosionLogger();

        //Misc
        getLogger().info("Registering Miscs...");
        new HunkaKittLoveParticles();

        //Commands
        getLogger().info("Registering Commands...");
        new SetPrefix();
        new SetSuffix();
        new RemoveName();

        millis = System.currentTimeMillis() - millis;
        getLogger().info("----------\nDone loading in " + millis + "ms");
    }

    public static Core getInstance() {
        return instance;
    }

    public FileManager getFileManager() {
        return fileManager;
    }
}
