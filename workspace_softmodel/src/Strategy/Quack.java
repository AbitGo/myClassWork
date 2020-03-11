package Strategy;

public class Quack implements QuackBahavior {
    @Override
    public void quack() {
        System.out.println("呱呱叫");
    }
}
