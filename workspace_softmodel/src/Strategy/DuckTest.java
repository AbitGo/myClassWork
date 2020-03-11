package Strategy;

public class DuckTest {
    public static void main(String[] args) {
        //红头鸭
        Duck duck = new MallardDuck();
        duck.setFlyBehavior(new FlyWithWings());
        duck.setQuackBahavior(new Quack());
        duck.display();
        duck.performFly();
        duck.swim();
    }
}
