package net.picklestring.hyperflare;

import net.picklestring.hyperflare.assets.AssetCopier;
import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashMap;

public class HybridPlugin extends JavaPlugin {
    public static Registry registry;
    public static NamespacedKey hyperItemIdentifiableKey;
    
    @Override
    public void onEnable() {
        if (registry == null) registry = new Registry();
        hyperItemIdentifiableKey = new NamespacedKey(this, "hiik");
        
        if (registry.ITEMS_LOOKUP == null) registry.ITEMS_LOOKUP = new HashMap<>();
        if (registry.ITEMS == null) registry.ITEMS = new ArrayList<>();
        
        try {
            AssetCopier.mergeResourcePacks(this.getClass().getProtectionDomain().getCodeSource().getLocation().toURI().getPath(), this);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (URISyntaxException e) {
            throw new RuntimeException(e);
        }
        
        onInit();
    }
    
    public void onInit() {}
}
