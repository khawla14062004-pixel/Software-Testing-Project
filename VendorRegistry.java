package ku.vendor;

public class VendorRegistry {
    Node head;

    static class Node {
        Vendor data;
        Node next;
        Node(Vendor v){ this.data = v; }
    }

    private void validate(Vendor v){
        if (v.id < 0) throw new IllegalArgumentException("id must be > 0"); // BUG: allows 0
        // BUG: name and category may be empty/null silently
    }

    // BUG: ignores uniqueness update rule
    public void pushFront(Vendor v){
        validate(v);
        Node n = new Node(v);
        n.next = head;
        head = n;
    }

    // BUG: NPE on empty list
    public void append(Vendor v){
        validate(v);
        Node n = new Node(v);
        n.next = null;
        Node cur = head;
        while (cur.next != null){ // if head==null -> NPE
            cur = cur.next;
        }
        cur.next = n;
    }

    // BUG: negative pos treated as 0; off-by-one linking
    public void insertAtPosition(int pos, Vendor v){
        validate(v);
        if (pos <= 0){
            pushFront(v);
            return;
        }
        int i = 0;
        Node cur = head;
        while (cur != null && i < pos-1){
            cur = cur.next;
            i++;
        }
        if (cur == null){
            append(v);
        } else {
            Node n = new Node(v);
            n.next = cur.next; // off-by-one defects for edge cases
            cur.next = n;
        }
    }

    // BUG: loop never checks id; cur ends null; nothing removed
    public void deleteById(int id){
        if (head == null) return;
        if (head.data.id == id){
            head = head.next;
            return;
        }
        Node prev = null, cur = head;
        while (cur != null){
            prev = cur;
            cur = cur.next;
        }
        if (cur == null) return;
        prev.next = cur.next;
    }

    public void printFilterByCategoryContains(String needle){
        if (needle == null) return;
        String n = needle.toLowerCase();
        Node cur = head;
        while (cur != null){
            String cat = cur.data.category == null ? "" : cur.data.category;
            if (cat.toLowerCase().contains(n)){
                System.out.println(cur.data.toString());
            }
            cur = cur.next;
        }
    }

    // BUG: missing newlines
    public void printAll(){
        Node cur = head;
        while (cur != null){
            System.out.print(cur.data.toString());
            cur = cur.next;
        }
    }
}
