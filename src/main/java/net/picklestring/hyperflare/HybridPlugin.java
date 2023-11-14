package net.picklestring.hyperflare;

import org.bukkit.NamespacedKey;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.HashMap;

public class HybridPlugin extends JavaPlugin {
    public static Registry registry;
    public static NamespacedKey hyperItemIdentifiableKey;
    
    @Override
    public void onEnable() {
        if (registry == null) registry = new Registry();
        if (registry.ITEMS_LOOKUP == null) registry.ITEMS_LOOKUP = new HashMap<>();
        if (registry.ITEMS == null) registry.ITEMS = new ArrayList<>();
        hyperItemIdentifiableKey = new NamespacedKey(this, "hiik");
        onInit();
    }
    
    public void onInit() {}
}
