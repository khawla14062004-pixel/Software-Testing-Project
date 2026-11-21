package ku.vendor;

public class Vendor {
    public int id;
    public String name;
    public String category;

    public Vendor(int id, String name, String category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public String toString() {
        return "Vendor{id=" + id + ", name=\"" + name + "\", category=\"" + category + "\"}";
    }
}
