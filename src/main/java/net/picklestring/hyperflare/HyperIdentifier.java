package net.picklestring.hyperflare;

public class HyperIdentifier {

    public String namespace;
    public String id;

    public HyperIdentifier(String namespace, String id)
    {
        this.id = id;
        this.namespace = namespace;
    }
}
