package net.picklestring.hyperflare;

import com.comphenix.protocol.PacketType;
import com.comphenix.protocol.ProtocolLibrary;
import com.comphenix.protocol.ProtocolManager;
import com.comphenix.protocol.events.ListenerPriority;
import com.comphenix.protocol.events.PacketAdapter;
import com.comphenix.protocol.events.PacketContainer;
import com.comphenix.protocol.events.PacketEvent;
import net.picklestring.hyperflare.assets.AssetCopier;
import net.picklestring.hyperflare.assets.ModelGenerator;
import net.picklestring.hyperflare.items.ItemEvents;
import org.bukkit.entity.Player;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Random;

public final class HyperFlare extends JavaPlugin {

    public static File PluginFolder;
    
    @Override
    public void onEnable() {
        // Plugin startup logic
        PluginFolder = getDataFolder();
        getServer().getPluginManager().registerEvents(new ItemEvents(), this);
        getLogger().info(this.getResource("assets/").toString());
        this.getCommand("hyper").setExecutor(new MainCommand());
        ProtocolManager manager = ProtocolLibrary.getProtocolManager();
        getLogger().info(manager.toString());
        manager.addPacketListener(new PacketAdapter(this, ListenerPriority.NORMAL, PacketType.Play.Server.BLOCK_BREAK_ANIMATION) {
            @Override
            public void onPacketReceiving(PacketEvent event) {
                event.getPacket().getIntegers().write(0, new Random().nextInt(2000));
                event.getPacket().getIntegers().write(1, 1);
                getLogger().info("RAN THE LISTENER");
            }
        });
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}
