package net.picklestring.hyperflare.items;

import net.picklestring.hyperflare.HybridPlugin;
import net.picklestring.hyperflare.HyperIdentifier;
import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.persistence.PersistentDataType;

public class HyperItem {
    public HyperIdentifier ident;
    public HyperSettings itemSettings;
    
    public HyperItem(HyperSettings settings)
    {
        itemSettings = settings;
    }
    
    public void onLeftClick() {};
    public void onRightClick() {};
    
    public ItemStack generateItemStack(int amount)
    {
        ItemStack itemStack = new ItemStack(Material.MAGENTA_DYE, amount);
        ItemMeta meta = itemStack.getItemMeta();
        
        meta.getPersistentDataContainer().set(HybridPlugin.hyperItemIdentifiableKey, PersistentDataType.STRING, ident.getIdentifiableString());
        meta.setDisplayName(itemSettings.getDisplayName());
        
        itemStack.setItemMeta(meta);
        return itemStack;
    }
}
