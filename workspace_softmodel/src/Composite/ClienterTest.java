package Composite;

import java.io.File;

public class ClienterTest {
    public static void createTree(AbsortNode absortNode) throws Exception {
        File file = new File(absortNode.getName());
        File[] files = file.listFiles();
        for(File fi:files){
            //System.out.println(fi.getName());
            if(fi.isFile()){
                FilerNode filerNode = new FilerNode(fi.getAbsolutePath());
                absortNode.addAbsortNode(filerNode);
            }
            else if(fi.isDirectory()){
                CatalogNode catalogNode = new CatalogNode(fi.getAbsolutePath());
                absortNode.addAbsortNode(catalogNode);
                createTree(catalogNode);
            }
        }
    }

    public static void main(String[] args) {
        AbsortNode absortNode = new CatalogNode("D:\\redis-2.4.5-win32-win64");
        try {
            createTree(absortNode);
        }catch (Exception e){
            //System.out.println("ashkdjaskjgdkjasd");
        }
        absortNode.display();
    }
}
