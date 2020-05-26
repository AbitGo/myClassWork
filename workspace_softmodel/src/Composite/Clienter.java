package Composite;

import java.io.File;

public class Clienter {
    public static void createTree(Catalog catalog){
        File file = new File(catalog.getCatalogName());
        File[] fileList = file.listFiles();
        for(File file1:fileList){
            //如果是文件的
            if(file1.isFile()){
                catalog.addfileNameList(file1.getAbsolutePath());
            }
            //如果是一个文件夹
            else if(file1.isDirectory()){
                createTree(new Catalog(file1.getAbsolutePath()));
            }
        }
    }
    public static void main(String[] args) {
        Catalog catalog = new Catalog("D:\\redis-2.4.5-win32-win64");
        createTree(catalog);
        catalog.display();
    }
}
