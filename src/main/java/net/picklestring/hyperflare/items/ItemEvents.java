package net.picklestring.hyperflare.items;

import net.picklestring.hyperflare.HybridPlugin;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.persistence.PersistentDataType;

public class ItemEvents implements Listener {
    @EventHandler
    public void playerInteract(PlayerInteractEvent event) {
        ItemStack Item = event.getItem();
        if (!event.hasItem() && !Item.getItemMeta().getPersistentDataContainer().has(HybridPlugin.hyperItemIdentifiableKey, PersistentDataType.STRING)) return;
        HyperItem hyperItem = HybridPlugin.registry.ITEMS_LOOKUP.get(Item.getItemMeta().getPersistentDataContainer().get(HybridPlugin.hyperItemIdentifiableKey, PersistentDataType.STRING));
        
        switch (event.getAction())
        {
            case LEFT_CLICK_AIR:
            case LEFT_CLICK_BLOCK:
                hyperItem.onLeftClick();
            case RIGHT_CLICK_AIR:
            case RIGHT_CLICK_BLOCK:
                hyperItem.onRightClick();
        }
    }
    
}
