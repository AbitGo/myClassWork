package Strategy;

public class Squeak implements QuackBahavior {
    @Override
    public void quack() {
        System.out.println("橡皮鸭吱吱叫");
    }
}
