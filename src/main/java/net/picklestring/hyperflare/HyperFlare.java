package net.picklestring.hyperflare;

import net.picklestring.hyperflare.items.ItemEvents;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;

public final class HyperFlare extends JavaPlugin {

    public static File PluginFolder;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginFolder = getDataFolder();
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        this.getCommand("hyper").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
