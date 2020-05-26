package Composite;

public abstract class AbsortNode {
    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public AbsortNode(String name) {
        this.name = name;
    }

    //新增节点
    public void addAbsortNode(AbsortNode absortNode) throws Exception {
        throw new Exception("Invalid exception");
    }
    abstract void display();
}
