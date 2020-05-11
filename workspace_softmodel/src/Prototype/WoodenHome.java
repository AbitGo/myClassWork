package Prototype;

public class WoodenHome extends Home {
    @Override
    void beam() {
        System.out.println("使用木头横梁");
    }

    @Override
    void base() {
        System.out.println("使用木头地基");
    }

    @Override
    void pipe() {
        System.out.println("使用木头管道");
    }
}
