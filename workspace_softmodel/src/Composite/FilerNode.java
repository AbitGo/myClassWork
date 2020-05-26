package Composite;

public class FilerNode extends AbsortNode {
    public FilerNode(String name) {
        super(name);
    }

    @Override
    void display() {
        System.out.println(this.getName());
    }
}
