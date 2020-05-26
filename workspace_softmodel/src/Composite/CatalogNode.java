package Composite;

import java.util.ArrayList;
import java.util.List;

public class CatalogNode extends AbsortNode {
    private List<AbsortNode> absortNodesList = new ArrayList<>();

    public CatalogNode(String name) {
        super(name);
    }

    @Override
    public void addAbsortNode(AbsortNode absortNode){
        absortNodesList.add(absortNode);
    }

    @Override
    void display() {
        System.out.println(this.getName());
        for(AbsortNode catalog:absortNodesList){
            catalog.display();
        }
    }
}
