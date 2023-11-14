package net.picklestring.hyperflare;

import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

public class MainCommand implements CommandExecutor {
    @Override
    public boolean onCommand(CommandSender commandSender, Command command, String s, String[] args) {
        Player player = (Player)commandSender;
        switch (args[0])
        {
            case "get":
                return get(args, player);
        }
        return false;
    }
    
    public boolean get(String[] args, Player player)
    {
        ItemStack itemStack = HybridPlugin.registry.ITEMS_LOOKUP.get(args[1]).generateItemStack(1);
        player.getInventory().addItem(itemStack);
        return true;
    }
}
