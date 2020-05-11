package Prototype;

public class PrototypeTest {
    public static void main(String[] args) {
        Home home = new ConcreteHome();
        home.build();
        System.out.println();
        home = new WoodenHome();
        home.build();
    }

}
