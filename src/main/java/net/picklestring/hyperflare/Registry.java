package net.picklestring.hyperflare;

import net.picklestring.hyperflare.items.HyperItem;

import java.util.ArrayList;
import java.util.HashMap;

public class Registry {
    public ArrayList<HyperItem> ITEMS;
    public HashMap<String, HyperItem> ITEMS_LOOKUP;

    public HyperItem register(HyperIdentifier ident, HyperItem item)
    {
        item.ident = ident;
        ITEMS.add(item);
        ITEMS_LOOKUP.put(ident.getIdentifiableString(), item);
        return item;
    }
}
