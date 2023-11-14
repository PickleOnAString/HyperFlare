package net.picklestring.hyperflare.assets;

import net.picklestring.hyperflare.HyperFlare;
import net.picklestring.hyperflare.HyperIdentifier;

import java.io.File;

public class ModelGenerator {
    public static void BuildModel(HyperIdentifier ident)
    {
        File modelsFolder = new File(HyperFlare.PluginFolder, ("resource_pack/assets/"+ident.namespace+"/models/"));
        if(!modelsFolder.exists()) modelsFolder.mkdirs();
    }
}
