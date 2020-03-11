package Strategy;

public abstract class Duck {
    private FlyBehavior flyBehavior;
    private QuackBahavior quackBahavior;
    public Duck(){

    }

    public abstract void display();

    public void performFly(){
        flyBehavior.fly();
    }

    public void performQuack(){
        quackBahavior.quack();
    }

    public void swim(){
        System.out.println("All ducks float,even decoys!");
    }

    public void setFlyBehavior(FlyBehavior flyBehavior) {
        this.flyBehavior = flyBehavior;
    }

    public void setQuackBahavior(QuackBahavior quackBahavior) {
        this.quackBahavior = quackBahavior;
    }
}
