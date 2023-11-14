package net.picklestring.hyperflare;

import net.picklestring.hyperflare.items.ItemEvents;
import org.bukkit.plugin.java.JavaPlugin;

public final class HyperFlare extends JavaPlugin {
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        this.getCommand("hyper").setExecutor(new MainCommand());
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
