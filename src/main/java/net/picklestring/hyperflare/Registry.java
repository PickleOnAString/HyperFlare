package net.picklestring.hyperflare;

import net.picklestring.hyperflare.assets.ModelGenerator;
import net.picklestring.hyperflare.items.HyperItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Registry {
    public ArrayList<HyperItem> ITEMS;
    public HashMap<String, HyperItem> ITEMS_LOOKUP;
    public int modelDataCounter = 0;

    public HyperItem register(HyperIdentifier ident, HyperItem item)
    {
        item.ident = ident;
        ITEMS.add(item);
        ITEMS_LOOKUP.put(ident.getIdentifiableString(), item);
        modelDataCounter += 1;
        item.modelData = modelDataCounter;
        ModelGenerator.BuildModel(ident, "item", "minecraft:item/generated");
        ModelGenerator.ModifyCustomModelData(ident, "item", "minecraft:item/generated", "magenta_dye", modelDataCounter);
        return item;
    }
}
