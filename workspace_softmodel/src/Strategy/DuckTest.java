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

        //橡皮鸭
        Duck duck1 = new RubberDuck();
        duck1.setFlyBehavior(new FlyNoWay());
        duck1.setQuackBahavior(new MuteQuack());
        duck1.display();
        duck1.performFly();
        duck1.swim();
    }
}
