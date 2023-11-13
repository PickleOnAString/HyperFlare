package net.picklestring.hyperflare;

import net.picklestring.hyperflare.items.HyperItem;

import java.util.ArrayList;

public class Registry {
    public ArrayList<HyperItem> ITEMS;

    public HyperItem register(HyperIdentifier ident, HyperItem item)
    {
        return item;
    }
}
