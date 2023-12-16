package net.picklestring.hyperflare.assets;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import net.picklestring.hyperflare.HyperFlare;
import net.picklestring.hyperflare.HyperIdentifier;

import java.io.*;
import java.nio.file.Files;

public class ModelGenerator {
    public static Gson gson;
    public static String defaultModel = "{\n" +
        "  \"parent\": \"minecraft:item/generated\",\n" +
        "  \"textures\": {\n" +
        "    \"layer0\": \"if_you_see_this_uh_oh:example/example\"\n" +
        "  }\n" +
        "}\n";
    public static String customModelDataModel = "{\n" +
        "  \"parent\": \"minecraft:item/generated\",\n" +
        "  \"textures\": {\n" +
        "    \"layer0\": \"if_you_see_this_uh_oh:example/example\"\n" +
        "  },\n" +
        "  \"overrides\": [\n" +
        "   ]" +
        "}\n";
    
    public static void BuildModel(HyperIdentifier ident, String subPath, String parent)
    {
        File modelsFolder = new File(HyperFlare.PluginFolder, ("resource_pack/assets/"+ident.namespace+"/models/"+subPath+"/"));
        if(!modelsFolder.exists()) modelsFolder.mkdirs();
        
        File modelFile = new File(modelsFolder, (ident.id+".json"));
        if (!modelFile.exists()) {
            try {
                modelFile.createNewFile();
                try (FileWriter fileWriter = new FileWriter(modelFile.getPath())) {
                    fileWriter.write(defaultModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        JsonElement json;
        gson = new Gson();
        try {
            json = gson.fromJson(new FileReader(modelFile), JsonElement.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonObject = json.getAsJsonObject();
        jsonObject.addProperty("parent", parent);
        jsonObject.get("textures").getAsJsonObject().addProperty("layer0", (ident.namespace+":"+subPath+"/"+ident.id));
        try (FileWriter fileWriter = new FileWriter(modelFile.getPath())) {
            fileWriter.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    public static void ModifyCustomModelData(HyperIdentifier ident, String subPath, String parent, String baseMaterial, int modelData)
    {
        File modelsFolder = new File(HyperFlare.PluginFolder, ("resource_pack/assets/minecraft/models/"+subPath+"/"));
        if(!modelsFolder.exists()) modelsFolder.mkdirs();
        
        File modelFile = new File(modelsFolder, (baseMaterial+".json"));
        if (!modelFile.exists()) {
            try {
                modelFile.createNewFile();
                try (FileWriter fileWriter = new FileWriter(modelFile.getPath())) {
                    fileWriter.write(customModelDataModel);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        
        JsonElement json;
        gson = new Gson();
        try {
            json = gson.fromJson(new FileReader(modelFile), JsonElement.class);
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        JsonObject jsonObject = json.getAsJsonObject();
        jsonObject.addProperty("parent", parent);
        
        jsonObject.get("textures").getAsJsonObject().addProperty("layer0", ("minecraft:"+subPath+"/"+baseMaterial));
        
        JsonObject newOverrideObject = new JsonObject();
        newOverrideObject.addProperty("model", ident.namespace+":"+subPath+"/"+ident.id);
        
        JsonObject predicateObject = new JsonObject();
        predicateObject.addProperty("custom_model_data", modelData);
        
        newOverrideObject.add("predicate", predicateObject);
        jsonObject.get("overrides").getAsJsonArray().add(newOverrideObject);
        
        try (FileWriter fileWriter = new FileWriter(modelFile.getPath())) {
            fileWriter.write(jsonObject.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
