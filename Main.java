package ku.vendor;

public class Main {
    public static void main(String[] args) {
        VendorRegistry reg = new VendorRegistry();
        reg.pushFront(new Vendor(1, "Acme", "Books"));
        reg.append(new Vendor(2, "Beta", "Electronics"));
        reg.insertAtPosition(1, new Vendor(3, "Gamma", "Books"));
        reg.printAll();
        System.out.println();
        reg.printFilterByCategoryContains("book");
        reg.deleteById(2);
        reg.printAll();
    }
}
