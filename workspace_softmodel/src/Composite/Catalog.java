package Composite;

import java.util.ArrayList;
import java.util.List;

public class Catalog {
    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    //目录名称
    private String catalogName;

    public Catalog(String catalogName) {
        this.catalogName = catalogName;
    }

    private List<Catalog> catalogList = new ArrayList<>();
    private List<String> fileNameList = new ArrayList<>();

    public void addCatalog(Catalog catalog){
        this.catalogList.add(catalog);
    }

    public void addfileNameList(String fileName){
        this.fileNameList.add(fileName);
    }

    public void display(){
        for(Catalog catalog:catalogList){
            //递归显示目录
            catalog.display();
        }
        for(String fileName:fileNameList){
            System.out.println(fileName);
        }
    }

}
