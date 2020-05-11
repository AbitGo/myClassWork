package Prototype;

public class ConcreteHome extends Home {
    @Override
    void beam() {
        System.out.println("使用混凝土横梁");
    }

    @Override
    void base() {
        System.out.println("使用混凝土地基");
    }

    @Override
    void pipe() {
        System.out.println("使用塑料管道");
    }
}
