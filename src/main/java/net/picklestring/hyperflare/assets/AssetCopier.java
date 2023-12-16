package net.picklestring.hyperflare.assets;

import net.picklestring.hyperflare.HyperFlare;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Enumeration;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.jar.JarInputStream;

import static org.bukkit.Bukkit.getLogger;

public class AssetCopier {
    
    public static void mergeResourcePacks(String pluginJar, JavaPlugin plugin) throws IOException {
        copyAssetsFromPlugin(pluginJar, plugin);
        
        mergeTwoDirectories(new File(HyperFlare.PluginFolder.getPath() + "/temp/" + plugin.getName() + "/assets"), new File(HyperFlare.PluginFolder+"/resource_pack/assets/"));
    }
    
    public static void mergeTwoDirectories(File dir1, File dir2){
        getLogger().info("Moving " + dir1.toPath() + " to " + dir2);
        
        for (File file : dir1.listFiles()) {
            
            // Is a regular file?
            if (!file.isDirectory()) { // Is a regular file
                File newName = new File(dir2, file.getName());
                file.renameTo(newName);
                getLogger().info("Moved " + file.getAbsolutePath() + " to " + newName.getAbsolutePath());
            } else { // Is a directory
                File newName = new File(dir2, file.getName());
                newName.mkdirs();
                getLogger().info("Moving dir " + file.getAbsolutePath() + " to " + newName.getAbsolutePath());
                mergeTwoDirectories(file, newName);
            }
            
            file.delete();
        }
        
        dir1.delete();
    }
    
    public static void copyAssetsFromPlugin(String pluginJar, JavaPlugin plugin) throws IOException {
        depackJar(pluginJar, HyperFlare.PluginFolder.getPath()+"/temp/"+plugin.getName());
        
        getLogger().info("START CLEANING");
        
        File dir = new File(HyperFlare.PluginFolder.getPath()+"/temp/"+plugin.getName());
        dir.mkdirs();
        
        deleteFolder(dir, "assets");
    }
    
    public static void deleteFolder(File folder, String preserveFolder) {
        File[] files = folder.listFiles();
        if(files!=null) { //some JVMs return null for empty dirs
            for(File f: files) {
                getLogger().info("Cleaning: "+f.getName());
                if (!f.getName().startsWith(preserveFolder)) {
                    if (f.isDirectory()) {
                        deleteFolder(f, "NONE");
                    } else {
                        f.delete();
                    }
                }
            }
        }
        
        if (!folder.getName().startsWith("test"))
        {
            folder.delete();
        }
    }
    
    
    public static void depackJar(String path, String outFolder) throws IOException {
        java.util.jar.JarFile jarfile = new java.util.jar.JarFile(new java.io.File(path));
        java.util.Enumeration<java.util.jar.JarEntry> enu= jarfile.entries();
        while(enu.hasMoreElements())
        {
            String destdir = outFolder;
            java.util.jar.JarEntry je = enu.nextElement();
            
            java.io.File fl = new java.io.File(destdir, je.getName());
            if(!fl.exists())
            {
                fl.getParentFile().mkdirs();
                fl = new java.io.File(destdir, je.getName());
            }
            if(je.isDirectory())
            {
                continue;
            }
            java.io.InputStream is = jarfile.getInputStream(je);
            java.io.FileOutputStream fo = new java.io.FileOutputStream(fl);
            while(is.available()>0)
            {
                fo.write(is.read());
            }
            fo.close();
            is.close();
        }
    }
}
