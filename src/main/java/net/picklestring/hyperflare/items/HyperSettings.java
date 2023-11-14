package net.picklestring.hyperflare.items;

public class HyperSettings {
    private String DisplayName = "SET THIS";
    
    public String getDisplayName()
    {
        return DisplayName;
    }
    
    public HyperSettings setDisplayName(String name)
    {
        DisplayName = name;
        return this;
    }
}
