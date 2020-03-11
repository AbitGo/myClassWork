package Strategy;

public class MuteQuack implements QuackBahavior {
    @Override
    public void quack() {
        System.out.println("不会叫");
    }
}
