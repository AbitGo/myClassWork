package Strategy;

public class FlyNoWay implements FlyBehavior {
    @Override
    public void fly() {
        System.out.println("不能飞行");
    }
}
